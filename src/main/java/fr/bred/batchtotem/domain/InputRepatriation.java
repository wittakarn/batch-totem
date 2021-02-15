package fr.bred.batchtotem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class InputRepatriation extends TransactionDetail {
    private static final long serialVersionUID = 1L;

    public String countryCode;
    public String numberOfRecord;
    public String correspondingStobi;
    public String compte;
    public String refCorresponding;
    public String refDebit;
    public String currency;
    public String amountRap;
    public String euroAmount;
    public String costRap;
}
