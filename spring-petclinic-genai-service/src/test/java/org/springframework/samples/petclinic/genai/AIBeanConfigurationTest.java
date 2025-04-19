// package org.springframework.samples.petclinic.genai;

// import org.junit.jupiter.api.Test;
// import org.springframework.ai.chat.memory.ChatMemory;
// import org.springframework.ai.chat.memory.InMemoryChatMemory;
// import org.springframework.ai.embedding.EmbeddingModel;
// import org.springframework.ai.vectorstore.SimpleVectorStore;
// import org.springframework.ai.vectorstore.VectorStore;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.test.context.ActiveProfiles;
// import org.springframework.web.reactive.function.client.WebClient;

// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// @SpringBootTest(classes = AIBeanConfiguration.class)
// @ActiveProfiles("test")
// class AIBeanConfigurationTest {

//     @Autowired
//     private ChatMemory chatMemory;

//     @Autowired
//     private WebClient.Builder webClientBuilder;

//     @MockBean
//     private EmbeddingModel embeddingModel;

//     @Autowired
//     private VectorStore vectorStore;

//     @Test
//     void chatMemoryShouldBeCreated() {
//         assertNotNull(chatMemory);
//         assertTrue(chatMemory instanceof InMemoryChatMemory);
//     }

//     @Test
//     void webClientBuilderShouldBeLoaded() {
//         assertNotNull(webClientBuilder);
//     }

//     @Test
//     void vectorStoreShouldBeCreated() {
//         assertNotNull(vectorStore);
//         assertTrue(vectorStore instanceof SimpleVectorStore);
//     }
// }
