Feature: Login User with valid username and password

Scenario: Successful Login with Valid Credentials

Given User should Launch Edge browser
When User should Navigate to url "https://practicetestautomation.com/practice-test-login/"
And User should Verify that login page is visible successfully
Then Enter username "student" in Username field
And Enter password "Password123" in Password field
When Click 'Submit' button
Then Verify new page URL contains "practicetestautomation.com/logged-in-successfully/"
And Verify that "Congratulations" or "successfully logged in" text is visible
And Verify that 'Log out' button is displayed on the new page