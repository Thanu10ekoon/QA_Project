Feature: User authentication
  As a user
  I want to signup and login
  So that I can access protected resources

  Scenario: Successful signup and login
    Given a new user with username "dave" and password "pass789"
    When the user signs up
    And the user attempts to login with password "pass789"
    Then the login should be successful

  Scenario: Failed login with wrong password
    Given a new user with username "emma" and password "goodpass"
    When the user signs up
    And the user attempts to login with password "badpass"
    Then the login should fail
