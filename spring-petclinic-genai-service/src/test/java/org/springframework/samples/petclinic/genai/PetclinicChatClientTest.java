package org.springframework.samples.petclinic.genai;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.Generation;
import org.springframework.ai.chat.builder.ChatClientBuilder;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.Prompt;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetclinicChatClientTest {

    @Mock
    private ChatClient chatClient;

    @Mock
    private ChatClient.Builder builder;

    @Mock
    private ChatMemory chatMemory;

    @Mock
    private ChatClient.PromptBuilder promptBuilder;

    @Mock
    private ChatClient.UserMessageBuilder userMessageBuilder;

    @Mock
    private ChatResponse chatResponse;

    @Mock
    private Generation generation;

    private PetclinicChatClient petclinicChatClient;

    @BeforeEach
    void setUp() {
        when(builder.defaultSystem(anyString())).thenReturn(builder);
        when(builder.chatMemory(any(ChatMemory.class))).thenReturn(builder);
        when(builder.build()).thenReturn(chatClient);

        petclinicChatClient = new PetclinicChatClient(builder, chatMemory);
    }

    @Test
    void testExchangeSuccessful() {
        // Given
        String query = "Tell me about dog vaccines";
        String expectedResponse = "There are several core vaccines recommended for dogs including rabies, distemper, parvovirus, and adenovirus.";

        when(chatClient.prompt()).thenReturn(promptBuilder);
        when(promptBuilder.user(any())).thenReturn(promptBuilder);
        when(promptBuilder.call()).thenReturn(chatResponse);
        when(chatResponse.content()).thenReturn(expectedResponse);

        // When
        String response = petclinicChatClient.exchange(query);

        // Then
        assertEquals(expectedResponse, response);
        verify(chatClient).prompt();
    }

    @Test
    void testExchangeHandlesException() {
        // Given
        String query = "Tell me about dog vaccines";

        when(chatClient.prompt()).thenReturn(promptBuilder);
        when(promptBuilder.user(any())).thenReturn(promptBuilder);
        when(promptBuilder.call()).thenThrow(new RuntimeException("Service unavailable"));

        // When
        String response = petclinicChatClient.exchange(query);

        // Then
        assertEquals("Chat is currently unavailable. Please try again later.", response);
        verify(chatClient).prompt();
    }
}
