package fr.bred.batchtotem.properties;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "emailcontext")
public class EmailContextProperties implements Serializable {
    private static final long serialVersionUID = 1L;
    private RecipientProperties validationFailed;
}
