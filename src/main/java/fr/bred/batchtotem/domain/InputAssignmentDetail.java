package fr.bred.batchtotem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class InputAssignmentDetail extends TransactionDetail {
    private static final long serialVersionUID = 1L;

    public String beneficiaryCode;
    public String customerCode;
    public String stobiNumber;
    public String amountSent;
    public String amountReceived;
    public String initialPaymentDate;
    public String patternDcu;
    public String patternRap;
}
