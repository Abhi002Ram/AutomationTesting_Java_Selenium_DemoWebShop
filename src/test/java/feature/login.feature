Feature: Demo Web Shop Login

  @SmokeTest
  Scenario Outline: User Login with valid and invalid credentials
    Given Browser is open
    And User navigates to Demo Web Shop
    And User is on the login page
    When User enters "<username>" and "<password>"
    And Clicks on the login button
    Then User should see appropriate message

    Examples:
      | username                  | password         |
      | admin@microsoft.com       | admin123        |
      | invalid_user@example.com  | wrongpassword123 |
