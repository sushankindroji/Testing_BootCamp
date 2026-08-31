Feature: Login User with correct email and password

Scenario: Successful Login with Valid Credentials Automation Exercise
Given User should Launch Edge browser
When User should Navigate to url "http://automationexercise.com"
And User should Verify that home page is visible successfully
Then Click on 'Signup / Login' button
Then Verify 'Login to your account' is visible
And Enter correct email address and password
When Click 'login' button
When Verify that 'Logged in as username' is visible
When Click 'Delete Account' button
Then Verify that 'ACCOUNT DELETED!' is visible