/*
package com.api.stepdefinition;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
 import org.junit.Assert;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
 import yellowpepper.e2e.qa.helper.data.PetDataHelper;
 import yellowpepper.e2e.qa.helper.session.E2eSharedData;
 import yellowpepper.e2e.qa.model.pet.PetRequestDTO;
 import yellowpepper.e2e.qa.model.pet.PetResponseDTO;
 import yellowpepper.e2e.qa.util.TestDataUtil;
 
 @Component
 public class PetStepdefinitionV2 {
     @Autowired
     private E2eSharedData e2eSharedData;
 
     @Autowired
     private PetStepsDefinitions petSteps;
 
     @Autowired
     private PetDataHelper petDataHelper;
 
     @Given("a new pet {string} is created")
     public void a_new_pet_is_created(String petName) throws JsonProcessingException {
 
         e2eSharedData.setPetName(petName);
 
         PetRequestDTO payloadPet = petDataHelper.getPetPayload();
         payloadPet.setId( TestDataUtil.generateUuid());
         String json = TestDataUtil.toJson(payloadPet);
         PetResponseDTO petResponseDTO = petSteps.createPet(json);
         Assert.assertEquals(petName, petResponseDTO.getName());
    }
 }

 */
