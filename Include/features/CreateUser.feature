Feature: Create User

 Scenario: Create a new user successfully
   Given a user request with the following details
      | username | Zane   |
      | password | 123456 |
      | age      | 25     |
      | gender   | MALE   |
   When the request is sent to create a user
   Then the response status code should be 200
   And the response should contain the correct user details
