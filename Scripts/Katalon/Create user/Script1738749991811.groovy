import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import groovy.json.JsonSlurper as JsonSlurper
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

// Arrange
WS.comment('Arrange')
int ageInt = age.toInteger()
def data = [('age') : ageInt, ('username') : username, ('password') : password, ('gender') : gender]
TestObject request = findTestObject('Object Repository/POST a new user', data)

// Action
WS.comment('Action')
def response = WS.sendRequest(request)

// Assert
WS.comment('Assert')
WS.verifyResponseStatusCode(response, 200, FailureHandling.CONTINUE_ON_FAILURE)
WS.verifyElementPropertyValue(response, 'age', ageInt, FailureHandling.CONTINUE_ON_FAILURE)
WS.verifyElementPropertyValue(response, 'username', username, FailureHandling.CONTINUE_ON_FAILURE)
WS.verifyElementPropertyValue(response, 'password', password, FailureHandling.CONTINUE_ON_FAILURE)
WS.verifyElementPropertyValue(response, 'gender', gender, FailureHandling.CONTINUE_ON_FAILURE)

