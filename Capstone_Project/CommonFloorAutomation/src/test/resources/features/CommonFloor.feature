Feature: CommonFloor Home Page Verification

  Scenario: Verify CommonFloor home page loads successfully
    Given I open the CommonFloor website
    Then the page title should not be empty
    And the URL should contain "commonfloor"
