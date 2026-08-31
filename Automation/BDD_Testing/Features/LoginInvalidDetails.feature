Feature: Login User with incorrect email and password

Scenario: Login with Invalid Credentials

Given User should Launch Edge browser
When User should Navigate to url "http://automationexercise.com"
And User should Verify that home page is visible successfully
Then Click on 'Signup / Login' button
And Verify 'Login to your account' is visible
Then Enter incorrect email address and password
When Click 'login' button
Then Verify error message "Your email or password is incorrect!" is visible