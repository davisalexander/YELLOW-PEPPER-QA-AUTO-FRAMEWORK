@StoreCRUD
Feature: Create order via Restful API

  Background: Create pet
    Given user has access to endpoint "/pet"
    When user performs a request to create a new "dummy_pet" pet


  @CreateStore @BE-STORE008
  Scenario Outline: Create order
    Then user should get the response code <statusCode>
    Given user has access to endpoint <endpoint>
    When user performs a request to create a new order <orderId>
    Then user should get the response code <statusCode>
    Examples:
      | statusCode | endpoint       | orderId |
      | 200        | "/store/order" | 7777    |


  @ReadStoreInventory @BE-STORE009
  Scenario Outline: Read store inventory
    And user has access to endpoint <endpoint>
    When user performs a request to get store inventory
    Then user should get the response code <statusCode>
    Examples:
      | endpoint           | statusCode |
      | "/store/inventory" | 200        |


  @ReadStoreOrderById @ReadStoreOrder @BE-STORE010
  Scenario Outline: Read store order by ID
    And user has access to endpoint <endpoint>
    When user performs a request to get order <orderId> by id
    Then user should get the response code <statusCode>
    Examples:
      | endpoint        | orderId | statusCode |
      | "/store/order/" | 7777    | 200        |


  @DeleteStoreOrder @BE-STORE011
  Scenario Outline: Delete store order
    And user deletes the order <orderId>
    Then user should get the response code <statusCode>
    Examples:
      | orderId  | statusCode |
      | 7777     | 200        |
