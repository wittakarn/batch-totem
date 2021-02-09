package fr.bred.batchtotem;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "app.messages")
public class AppMessages {

    // Bind to app.messages.helloWorld property
    // private String helloWorld;

}
