package fr.bred.batchtotem.properties;

import java.io.Serializable;

import lombok.Data;

@Data
public class BatchUserProperties implements Serializable {
    private static final long serialVersionUID = 1L;

    private String applicationId;
    private String consumingId;
    private String consumingChannel;
    private String peo;
    private String userId;
    private String societyCode;
    private String tokenContext;
}
