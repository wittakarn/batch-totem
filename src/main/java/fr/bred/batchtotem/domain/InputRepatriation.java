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

    public String codePays;
    public String nbRap;
    public String corrStobi;
    public String compte;
    public String refCor;
    public String refDebit;
    public String devise;
    public String mtRap;
    public String mtEur;
    public String fraisRap;
}
