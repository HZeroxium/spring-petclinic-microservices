// package org.springframework.samples.petclinic.genai;

// import com.fasterxml.jackson.core.JsonProcessingException;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.samples.petclinic.genai.dto.OwnerDetails;
// import org.springframework.samples.petclinic.genai.dto.PetDetails;
// import org.springframework.samples.petclinic.genai.dto.PetRequest;
// import org.springframework.samples.petclinic.genai.dto.Vet;
// import org.springframework.test.context.ActiveProfiles;
// import org.springframework.test.context.junit.jupiter.SpringExtension;

// import java.util.List;
// import java.util.function.Function;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// @ExtendWith(MockitoExtension.class)
// class AIFunctionConfigurationTest {

//     @Mock
//     private AIDataProvider aiDataProvider;

//     private final AIFunctionConfiguration aiFunctionConfiguration = new AIFunctionConfiguration();

//     @Test
//     void testListOwners() {
//         // Given
//         List<OwnerDetails> owners = List.of(
//                 new OwnerDetails(1, "John", "Doe", "123 Main St", "New York", "1234567890", List.of()),
//                 new OwnerDetails(2, "Jane", "Smith", "456 Elm St", "Boston", "0987654321", List.of()));
//         OwnersResponse expectedResponse = new OwnersResponse(owners);
//         when(aiDataProvider.getAllOwners()).thenReturn(expectedResponse);

//         // When
//         Function<OwnerRequest, OwnersResponse> function = aiFunctionConfiguration.listOwners(aiDataProvider);
//         OwnersResponse result = function.apply(new OwnerRequest("", "", "", "", ""));

//         // Then
//         assertEquals(expectedResponse, result);
//         verify(aiDataProvider).getAllOwners();
//     }

//     @Test
//     void testAddOwnerToPetclinic() {
//         // Given
//         OwnerDetails owner = new OwnerDetails(1, "Alice", "Johnson", "789 Pine St", "Chicago", "5551234567", List.of());
//         OwnerResponse expectedResponse = new OwnerResponse(owner);
//         OwnerRequest request = new OwnerRequest("Alice", "Johnson", "789 Pine St", "Chicago", "5551234567");
//         when(aiDataProvider.addOwnerToPetclinic(request)).thenReturn(expectedResponse);

//         // When
//         Function<OwnerRequest, OwnerResponse> function = aiFunctionConfiguration.addOwnerToPetclinic(aiDataProvider);
//         OwnerResponse result = function.apply(request);

//         // Then
//         assertEquals(expectedResponse, result);
//         verify(aiDataProvider).addOwnerToPetclinic(request);
//     }

//     @Test
//     void testListVets() throws JsonProcessingException {
//         // Given
//         List<String> vetData = List.of("Vet 1 info", "Vet 2 info");
//         VetResponse expectedResponse = new VetResponse(vetData);
//         Vet vet = new Vet();
//         vet.setFirstName("James");
//         VetRequest request = new VetRequest(vet);
//         when(aiDataProvider.getVets(request)).thenReturn(expectedResponse);

//         // When
//         Function<VetRequest, VetResponse> function = aiFunctionConfiguration.listVets(aiDataProvider);
//         VetResponse result = function.apply(request);

//         // Then
//         assertEquals(expectedResponse, result);
//         verify(aiDataProvider).getVets(request);
//     }

//     @Test
//     void testListVetsHandlesException() throws JsonProcessingException {
//         // Given
//         Vet vet = new Vet();
//         vet.setFirstName("James");
//         VetRequest request = new VetRequest(vet);
//         when(aiDataProvider.getVets(request)).thenThrow(JsonProcessingException.class);

//         // When
//         Function<VetRequest, VetResponse> function = aiFunctionConfiguration.listVets(aiDataProvider);
//         VetResponse result = function.apply(request);

//         // Then
//         assertNull(result);
//         verify(aiDataProvider).getVets(request);
//     }

//     @Test
//     void testAddPetToOwner() {
//         // Given
//         PetDetails pet = new PetDetails(1, "Buddy", "2020-01-01", 1, "Dog", "John Doe");
//         AddedPetResponse expectedResponse = new AddedPetResponse(pet);
//         PetRequest petRequest = new PetRequest(null, "2020-01-01", "Buddy", 1);
//         AddPetRequest request = new AddPetRequest(petRequest, 1);
//         when(aiDataProvider.addPetToOwner(request)).thenReturn(expectedResponse);

//         // When
//         Function<AddPetRequest, AddedPetResponse> function = aiFunctionConfiguration.addPetToOwner(aiDataProvider);
//         AddedPetResponse result = function.apply(request);

//         // Then
//         assertEquals(expectedResponse, result);
//         verify(aiDataProvider).addPetToOwner(request);
//     }
// }
