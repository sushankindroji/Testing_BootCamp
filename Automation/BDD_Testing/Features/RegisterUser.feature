Feature: Register User

Scenario: Successful User Registration

Given User should Launch Edge browser
When User should Navigate to url "http://automationexercise.com"
And User should Verify that home page is visible successfully
Then Click on 'Signup / Login' button
And Verify 'New User Signup!' is visible
Then Enter name and email address
When Click 'Signup' button
Then Verify that 'ENTER ACCOUNT INFORMATION' is visible
And Fill details: Title, Name, Email, Password, Date of birth
And Select checkbox 'Sign up for our newsletter!'
And Select checkbox 'Receive special offers from our partners!'
And Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
When Click 'Create Account' button
Then Verify that 'ACCOUNT CREATED!' is visible
When Click 'Continue' button
Then Verify that 'Logged in as username' is visible
When Click 'Delete Account' button
Then Verify that 'ACCOUNT DELETED!' is visible
And Click 'Continue' button