     @PetCRUD  @ReadPet @UpdatePet @DeletePet
      Feature: Create pet via Restful API

      Background: create pet
        Given user has access to endpoint "/pet"
        When user performs a request to create a new "dummy_pet" pet

      @CreatePet @BE-PET001
      Scenario: Create pet
        Then user should get the response code 200

      @ReadPetByStatus @ReadPet @BE-PET002
      Scenario Outline: Read pet by Status
        And user has access to endpoint "/pet/findByStatus"
        When user performs a request to get pet by status "<status>"
        Then user should get the response code 200

        Examples:
          | status    |
          | sold      |
          | available |
          | pending   |

      @ReadPetById @ReadPet @BE-PET003
      Scenario: Read pet by ID
        And user has access to endpoint "/pet/"
        When user performs a request to get pet "7777" by id
        Then user should get the response code 200

      @ReadPetByTag @ReadPet @BE-PET004
      Scenario: Read pet by tags
        And user has access to endpoint "/pet/"
        When user performs a request to get pet "7777" by id
        Then user should get the response code 200

      @UpdatePet @BE-PET005
      Scenario: Update pet
        And user updates the pet "7777"
        Then user should get the response code 200

      @UpdatePet @BE-PET006
      Scenario: Delete pet
        And user deletes the pet "7777"
        Then user should get the response code 200
