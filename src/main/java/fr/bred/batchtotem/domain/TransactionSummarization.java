package fr.bred.batchtotem.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransactionSummarization implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer recordCount;
    private BigDecimal summarizeOfMtemis;
    private BigDecimal summarizeOfMtrecu;

    public TransactionSummarization() {
        recordCount = 0;
        summarizeOfMtemis = BigDecimal.ZERO;
        summarizeOfMtrecu = BigDecimal.ZERO;
    }
}
