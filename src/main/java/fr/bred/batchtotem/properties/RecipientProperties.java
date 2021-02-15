package fr.bred.batchtotem.properties;

import java.util.List;

import lombok.Data;

@Data
public class RecipientProperties {
    private String from;
    private List<String> to;
    private List<String> cc;
}
