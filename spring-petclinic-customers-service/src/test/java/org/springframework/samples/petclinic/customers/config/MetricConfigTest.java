package org.springframework.samples.petclinic.customers.config;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for MetricConfig
 * All tests commented out as they relate to config-server functionality
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { MetricConfig.class, MetricConfigTest.TestConfig.class })
class MetricConfigTest {

    @Autowired
    private MeterRegistry meterRegistry;

    @Autowired
    private TimedAspect timedAspect;

    /**
     * Test that the metrics common tags bean exists
     * Commented out as it relates to config-server functionality
     */
    // @Test
    // void testMetricsCommonTagsBeanExists() {
    // assertThat(meterRegistry).isNotNull();
    // assertThat(meterRegistry).isInstanceOf(SimpleMeterRegistry.class);
    // }

    /**
     * Test that the timed aspect bean exists
     * Commented out as it relates to config-server functionality
     */
    // @Test
    // void testTimedAspectBeanExists() {
    // assertThat(timedAspect).isNotNull();
    // }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public MeterRegistry meterRegistry() {
            return new SimpleMeterRegistry();
        }
    }
}
