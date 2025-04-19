// package org.springframework.samples.petclinic.genai;

// import com.fasterxml.jackson.core.JsonProcessingException;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.ai.document.Document;
// import org.springframework.ai.vectorstore.SearchRequest;
// import org.springframework.ai.vectorstore.VectorStore;
// import org.springframework.core.ParameterizedTypeReference;
// import org.springframework.samples.petclinic.genai.dto.OwnerDetails;
// import org.springframework.samples.petclinic.genai.dto.PetDetails;
// import org.springframework.samples.petclinic.genai.dto.PetRequest;
// import org.springframework.samples.petclinic.genai.dto.Vet;
// import org.springframework.web.reactive.function.client.WebClient;
// import reactor.core.publisher.Mono;

// import java.util.List;
// import java.util.function.Function;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.*;
// import static org.mockito.Mockito.*;

// @ExtendWith(MockitoExtension.class)
// class AIDataProviderTest {

//     @Mock
//     private WebClient.Builder webClientBuilder;

//     @Mock
//     private WebClient webClient;

//     @Mock
//     private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

//     @Mock
//     private WebClient.RequestHeadersSpec requestHeadersSpec;

//     @Mock
//     private WebClient.RequestBodyUriSpec requestBodyUriSpec;

//     @Mock
//     private WebClient.RequestBodySpec requestBodySpec;

//     @Mock
//     private WebClient.ResponseSpec responseSpec;

//     @Mock
//     private VectorStore vectorStore;

//     private AIDataProvider aiDataProvider;

//     @BeforeEach
//     void setUp() {
//         when(webClientBuilder.build()).thenReturn(webClient);
//         aiDataProvider = new AIDataProvider(webClientBuilder, vectorStore);
//     }

//     @Test
//     void testGetAllOwners() {
//         // Given
//         List<OwnerDetails> mockOwners = List.of(
//                 new OwnerDetails(1, "John", "Doe", "123 Main St", "New York", "1234567890", List.of()),
//                 new OwnerDetails(2, "Jane", "Smith", "456 Elm St", "Boston", "0987654321", List.of()));

//         when(webClient.get()).thenReturn(requestHeadersUriSpec);
//         when(requestHeadersUriSpec.uri(contains("customers-service"))).thenReturn(requestHeadersSpec);
//         when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
//         when(responseSpec.bodyToMono(any(ParameterizedTypeReference.class))).thenReturn(Mono.just(mockOwners));

//         // When
//         OwnersResponse response = aiDataProvider.getAllOwners();

//         // Then
//         assertNotNull(response);
//         assertEquals(2, response.owners().size());
//         assertEquals("John", response.owners().get(0).firstName());
//         assertEquals("Jane", response.owners().get(1).firstName());
//         verify(webClient).get();
//     }

//     @Test
//     void testGetVets() throws JsonProcessingException {
//         // Given
//         List<Document> mockDocuments = List.of(
//                 Document.from("Vet 1 info"),
//                 Document.from("Vet 2 info"));

//         when(vectorStore.similaritySearch(any(SearchRequest.class))).thenReturn(mockDocuments);

//         Vet mockVet = new Vet();
//         mockVet.setName("Dr. Smith");
//         VetRequest request = new VetRequest(mockVet);

//         // When
//         VetResponse response = aiDataProvider.getVets(request);

//         // Then
//         assertNotNull(response);
//         assertEquals(2, response.vet().size());
//         assertEquals("Vet 1 info", response.vet().get(0));
//         assertEquals("Vet 2 info", response.vet().get(1));
//         verify(vectorStore).similaritySearch(any(SearchRequest.class));
//     }

//     @Test
//     void testAddPetToOwner() {
//         // Given
//         PetDetails mockPetDetails = new PetDetails(1, "Buddy", "2020-01-01", 1, "Dog", "John Doe");
//         PetRequest petRequest = new PetRequest(null, "2020-01-01", "Buddy", 1);
//         AddPetRequest request = new AddPetRequest(petRequest, 1);

//         when(webClient.post()).thenReturn(requestBodyUriSpec);
//         when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
//         when(requestBodySpec.bodyValue(any())).thenReturn(requestHeadersSpec);
//         when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
//         when(responseSpec.bodyToMono(PetDetails.class)).thenReturn(Mono.just(mockPetDetails));

//         // When
//         AddedPetResponse response = aiDataProvider.addPetToOwner(request);

//         // Then
//         assertNotNull(response);
//         assertEquals("Buddy", response.pet().name());
//         assertEquals(1, response.pet().id());
//         verify(webClient).post();
//     }

//     @Test
//     void testAddOwnerToPetclinic() {
//         // Given
//         OwnerDetails mockOwnerDetails = new OwnerDetails(1, "Alice", "Johnson", "789 Pine St", "Chicago", "5551234567",
//                 List.of());
//         OwnerRequest request = new OwnerRequest("Alice", "Johnson", "789 Pine St", "Chicago", "5551234567");

//         when(webClient.post()).thenReturn(requestBodyUriSpec);
//         when(requestBodyUriSpec.uri(contains("customers-service"))).thenReturn(requestBodySpec);
//         when(requestBodySpec.bodyValue(any())).thenReturn(requestHeadersSpec);
//         when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
//         when(responseSpec.bodyToMono(OwnerDetails.class)).thenReturn(Mono.just(mockOwnerDetails));

//         // When
//         OwnerResponse response = aiDataProvider.addOwnerToPetclinic(request);

//         // Then
//         assertNotNull(response);
//         assertEquals("Alice", response.owner().firstName());
//         assertEquals("Johnson", response.owner().lastName());
//         verify(webClient).post();
//     }
// }
