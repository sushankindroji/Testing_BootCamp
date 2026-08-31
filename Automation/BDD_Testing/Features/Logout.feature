Feature: Logout User

Scenario: Successful Logout

Given User should Launch Edge browser
When User should Navigate to url "http://automationexercise.com"
And User should Verify that home page is visible successfully
Then Click on 'Signup / Login' button
And Verify 'Login to your account' is visible
Then Enter correct email address and password
When Click 'login' button
Then Verify that 'Logged in as username' is visible
When Click 'Logout' button
Then Verify that user is navigated to login page