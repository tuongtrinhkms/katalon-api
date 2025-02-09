package testng;

import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.kms.katalon.core.util.KeywordUtil;
import com.kms.katalon.core.testobject.TestObject;
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords;
import com.kms.katalon.core.model.FailureHandling;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import testng.model.UserRequest;

import static io.restassured.RestAssured.*;
import org.testng.Assert;

public class UserTest {
	private SoftAssert softAssert = new SoftAssert();
	
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://sample-web-service-aut.herokuapp.com";
	}
	
    @Test
    public void testCreateUser() {
        // Arrange
    	UserRequest request = new UserRequest("Zane", "123456", 25, "MALE", null);
    	
    	// Action
        Response response = given()
                .header("Content-Type", "application/json")
                .body(request)
                .post("/api/users/json");
        JsonPath json = response.jsonPath();
        int statusCode = response.getStatusCode();
        
        // Assert
        softAssert.assertEquals(statusCode, 200, "Status code is incorrect");
        softAssert.assertEquals(json.getString("username"), request.username, "Username is incorrect");
        softAssert.assertEquals(json.getString("password"), request.password, "Password is incorrect");
        softAssert.assertEquals(json.getInt("age"), request.age, "Age is incorrect");
        softAssert.assertEquals(json.getString("gender"), request.gender, "Gender is incorrect");
        softAssert.assertAll();
    }
}