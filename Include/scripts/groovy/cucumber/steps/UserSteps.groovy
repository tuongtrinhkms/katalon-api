package cucumber.steps
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import io.cucumber.datatable.DataTable
import io.restassured.path.json.JsonPath
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification
import testng.model.UserRequest

import org.openqa.selenium.WebElement
import org.testng.asserts.SoftAssert
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When

import static io.restassured.RestAssured.*;



class UserSteps {
	private RequestSpecification request;
	private Response response;
	private UserRequest userRequest;
	private SoftAssert softAssert = new SoftAssert();
	private String baseUri = "https://sample-web-service-aut.herokuapp.com"

	@Given("a user request with the following details")
	def a_user_request_with_the_following_details(DataTable dataTable) {
		Map<String, String> userData = dataTable.asMap(String.class, String.class);
		userRequest = new UserRequest(
				userData.get("username"),
				userData.get("password"),
				Integer.parseInt(userData.get("age")),
				userData.get("gender"),
				null);
		request = given()
				.baseUri(baseUri)
				.header("Content-Type", "application/json")
				.body(userRequest);
	}

	@When("the request is sent to create a user")
	def the_request_is_sent_to_create_a_user() {
		response = request.post("/api/users/json");
	}

	@Then("the response status code should be {int}")
	def the_response_status_code_should_be(int expectedStatusCode) {
		softAssert.assertEquals(response.getStatusCode(), expectedStatusCode, "Status code is incorrect");
		softAssert.assertAll();
	}

	@Then("the response should contain the correct user details")
	def the_response_should_contain_the_correct_user_details() {
		JsonPath json = response.jsonPath();
		softAssert.assertEquals(json.getString("username"), userRequest.username, "Username is incorrect");
		softAssert.assertEquals(json.getString("password"), userRequest.password, "Password is incorrect");
		softAssert.assertEquals(json.getInt("age"), userRequest.age, "Age is incorrect");
		softAssert.assertEquals(json.getString("gender"), userRequest.gender, "Gender is incorrect");
		softAssert.assertAll();
	}
}