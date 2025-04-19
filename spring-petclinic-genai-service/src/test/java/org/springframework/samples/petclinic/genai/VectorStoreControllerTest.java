// package org.springframework.samples.petclinic.genai;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.ai.document.Document;
// import org.springframework.ai.reader.JsonReader;
// import org.springframework.ai.vectorstore.VectorStore;
// import org.springframework.boot.context.event.ApplicationStartedEvent;
// import org.springframework.core.ParameterizedTypeReference;
// import org.springframework.core.io.ByteArrayResource;
// import org.springframework.core.io.Resource;
// import org.springframework.samples.petclinic.genai.dto.Vet;
// import org.springframework.web.reactive.function.client.WebClient;
// import reactor.core.publisher.Mono;

// import java.io.IOException;
// import java.util.Arrays;
// import java.util.List;

// import static org.mockito.ArgumentMatchers.*;
// import static org.mockito.Mockito.*;

// @ExtendWith(MockitoExtension.class)
// class VectorStoreControllerTest {

// @Mock
// private WebClient.Builder webClientBuilder;

// @Mock
// private WebClient webClient;

// @Mock
// private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

// @Mock
// private WebClient.RequestHeadersSpec requestHeadersSpec;

// @Mock
// private WebClient.ResponseSpec responseSpec;

// @Mock
// private VectorStore vectorStore;

// @Mock
// private ApplicationStartedEvent applicationStartedEvent;

// private VectorStoreController vectorStoreController;

// @BeforeEach
// void setUp() {
// when(webClientBuilder.build()).thenReturn(webClient);
// vectorStoreController = new VectorStoreController(webClientBuilder,
// vectorStore);
// }

// @Test
// void loadVetDataToVectorStoreOnStartupTest() throws IOException {
// // Given
// List<Vet> mockVets = Arrays.asList(
// createVet(1, "James", "Carter"),
// createVet(2, "Helen", "Leary"));

// // Mock WebClient behavior
// when(webClient.get()).thenReturn(requestHeadersUriSpec);
// when(requestHeadersUriSpec.uri(contains("vets-service"))).thenReturn(requestHeadersSpec);
// when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
// when(responseSpec.bodyToMono(any(ParameterizedTypeReference.class))).thenReturn(Mono.just(mockVets));

// // Mock vectorStore behavior
// doNothing().when(vectorStore).add(anyList());

// // When
// vectorStoreController.loadVetDataToVectorStoreOnStartup(applicationStartedEvent);

// // Then
// verify(webClient).get();
// verify(vectorStore).add(anyList());
// }

// private Vet createVet(int id, String firstName, String lastName) {
// Vet vet = new Vet();
// vet.setId(id);
// vet.setFirstName(firstName);
// vet.setLastName(lastName);
// return vet;
// }
// }
