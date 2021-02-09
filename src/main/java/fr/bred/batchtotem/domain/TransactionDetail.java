package fr.bred.batchtotem.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class TransactionDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    public String type;
}
