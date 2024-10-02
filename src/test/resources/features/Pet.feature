@PetCRUD
Feature: Create pet via Restful API

  Background: create pet
    Given user has access to endpoint "/pet"
    When user performs a request to create a new "dummy_pet" pet


  @CreatePet @BE-PET001
  Scenario Outline: Create pet
    Then user should get the response code <statusCode>
    Examples:
      | statusCode |
      | 200        |


  @ReadPetByStatus @ReadPet @BE-PET002
  Scenario Outline: Read pet by Status
    And user has access to endpoint "<endpoint>"
    When user performs a request to get pet by status "<status>"
    Then user should get the response code <statusCode>

    Examples:
      | status         | endpoint          | statusCode |
      | sold           | /pet/findByStatus | 200        |
      | available      | /pet/findByStatus | 200        |
      | pending        | /pet/findByStatus | 200        |


  @ReadPetById @ReadPet @BE-PET003
  Scenario Outline: Read pet by ID
    And user has access to endpoint <endpoint>
    When user performs a request to get pet <petId> by id
    Then user should get the response code <statusCode>
    Examples:
      | endpoint | petId  | statusCode |
      | "/pet/"  | "7777" | 200        |


  @ReadPetByTag @ReadPet @BE-PET004
  Scenario Outline: Read pet by tags
    And user has access to endpoint <endpoint>
    When user performs a request to get pet <petId> by id
    Then user should get the response code <statusCode>
    Examples:
      | endpoint | petId  | statusCode |
      | "/pet/"  | "7777" | 200        |


  @UpdatePet @BE-PET005
  Scenario Outline: Update pet
    And user updates the pet <petId>
    Then user should get the response code <statusCode>
    Examples:
      | petId  | statusCode |
      | "7777" | 200        |


  @DeletePet @BE-PET006
  Scenario Outline: Delete pet
    And user deletes the pet <petId>
    Then user should get the response code <statusCode>
    Examples:
      | petId  | statusCode |
      | "7777" | 200        |
