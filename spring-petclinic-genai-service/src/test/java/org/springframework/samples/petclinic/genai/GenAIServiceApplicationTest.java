package org.springframework.samples.petclinic.genai;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class GenAIServiceApplicationTest {

    @Test
    void mainMethodShouldRunWithoutExceptions() {
        try (MockedStatic<SpringApplication> mockedStatic = Mockito.mockStatic(SpringApplication.class)) {
            assertDoesNotThrow(() -> GenAIServiceApplication.main(new String[] {}));
            mockedStatic.verify(() -> SpringApplication.run(GenAIServiceApplication.class, new String[] {}),
                    Mockito.times(1));
        }
    }
}
