package org.springframework.samples.petclinic.vets.system;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.cache.CacheManager;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for CacheConfig
 * All tests commented out as they relate to config-server functionality
 */
class CacheConfigTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withUserConfiguration(CacheConfig.class)
            .withConfiguration(
                    org.springframework.boot.autoconfigure.AutoConfigurations.of(CacheAutoConfiguration.class));

    /**
     * Test that cache is enabled in production profile
     * Commented out as it relates to config-server functionality
     */
    // @Test
    // void cacheShouldBeEnabledInProductionProfile() {
    // contextRunner.withPropertyValues("spring.profiles.active=production")
    // .run(context -> assertThat(context).hasSingleBean(CacheManager.class));
    // }

    /**
     * Test that cache is disabled in other profiles
     * Commented out as it relates to config-server functionality
     */
    // @Test
    // void cacheShouldBeDisabledInOtherProfiles() {
    // contextRunner.withPropertyValues("spring.profiles.active=test")
    // .run(context -> assertThat(context).doesNotHaveBean(CacheManager.class));
    // }
}
