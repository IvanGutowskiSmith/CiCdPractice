Feature: Basic UI Loading Verification

  Scenario: Verify homepage title
    When the user navigates to "https://example.com"
    Then the page title should contain "Example Domain"


  Scenario: Verify joke api returns a valid setup and delivery
    Given the user sends a GET request to the joke api
    Then the response status code should be 200
    And the joke response should contain a valid setup and delivery