@UserCRUD
Feature: Create users via Restful API

  Background: Create user
    Given user has access to endpoint "/user"
    When user performs a request to create a new user "theUser"


  @CreateUser @BE-STORE012
  Scenario Outline: Create user
    Then user should get the response code <statusCode>
    Examples:
      | statusCode |
      | 200        |


  @GetUserLogin @BE-USER014
  Scenario Outline: Read user login
    Given user has access to endpoint <endpoint>
    When user performs a request to get user login session
    Then user should get their login session
    Examples:
      | endpoint      |
      | "/user/login" |


  @GetUserLogout @BE-USER015
  Scenario Outline: Read user logout
    Given user has access to endpoint <endpoint>
    When user performs a request to get user logout
    Then user should get the response code <statusCode>

    Examples:
      | endpoint      | statusCode |
      | "/user/logout"| 200        |


  @UpdateUser @BE-USER017
  Scenario Outline: Update user
    Given user has access to endpoint "/user/"
    When user updates the user <userName>
    Then user should get the response code <statusCode>
    Examples:
      | userName      | statusCode |
      | "theUser"     | 200        |


  @DeleteUser @BE-USER018
  Scenario Outline: Delete user
    Given user has access to endpoint "/user/"
    When user deletes the user <userName>
    Then user should get the response code <statusCode>
    Examples:
      | userName  | statusCode |
      | "theUser" | 200        |