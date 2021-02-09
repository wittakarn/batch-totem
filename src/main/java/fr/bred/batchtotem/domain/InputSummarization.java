package fr.bred.batchtotem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class InputSummarization extends TransactionDetail {
    private static final long serialVersionUID = 1L;

    public String totalInputAssignmentDetail;
    public String summarizeOfMtemis;
    public String summarizeOfMtrecu;
}
