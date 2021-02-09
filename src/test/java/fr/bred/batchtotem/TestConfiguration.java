package fr.bred.batchtotem;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import fr.bred.batchtotem.config.BatchTotemConfiguration;

/**
 * The Class TestConfiguration.
 */
@Configuration
@Profile("test")
@PropertySources({ //
        @PropertySource("classpath:/application-test.yml"), //
})
@Import(BatchTotemConfiguration.class)
public class TestConfiguration {
}
