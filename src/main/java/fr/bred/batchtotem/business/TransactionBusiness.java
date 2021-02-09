package fr.bred.batchtotem.business;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import fr.bred.batchtotem.domain.InputSummarization;
import fr.bred.batchtotem.domain.TransactionSummarization;
import fr.bred.batchtotem.util.NumberUtil;

@Component
public class TransactionBusiness {

    private TransactionSummarization transactionSummarization;
    private InputSummarization inputSummarization;

    public TransactionBusiness() {
        transactionSummarization = new TransactionSummarization();
    }

    public void updateTransactionSummary(String summarizeOfMtemisStr, String summarizeOfMtrecuStr) {
        BigDecimal summarizeOfMtemis = NumberUtil.getBigDecimalValue(summarizeOfMtemisStr);
        BigDecimal summarizeOfMtrecu = NumberUtil.getBigDecimalValue(summarizeOfMtrecuStr);

        transactionSummarization.setRecordCount(transactionSummarization.getRecordCount() + 1);
        transactionSummarization.setSummarizeOfMtemis(transactionSummarization.getSummarizeOfMtemis().add(summarizeOfMtemis));
        transactionSummarization.setSummarizeOfMtrecu(transactionSummarization.getSummarizeOfMtrecu().add(summarizeOfMtrecu));
    }

    public void setInputSummarization(InputSummarization inputSummarization) {
        this.inputSummarization = inputSummarization;
    }

    public String getSummarizationResult() {
        Integer totalInputAssignmentDetail = inputSummarization.getTotalInputAssignmentDetail() == null || "".equals(inputSummarization.getTotalInputAssignmentDetail()) //
                ? 0 : Integer.parseInt(inputSummarization.getTotalInputAssignmentDetail());
        BigDecimal summarizeOfMtemis = NumberUtil.getBigDecimalValue(inputSummarization.getSummarizeOfMtemis());
        BigDecimal summarizeOfMtrecu = NumberUtil.getBigDecimalValue(inputSummarization.getSummarizeOfMtrecu());

        if (totalInputAssignmentDetail.intValue() != transactionSummarization.getRecordCount()) {
            return "Record count incorrect";
        }

        if (summarizeOfMtemis.compareTo(transactionSummarization.getSummarizeOfMtemis()) != 0) {
            return "Mtemis summary incorrect";
        }

        if (summarizeOfMtrecu.compareTo(transactionSummarization.getSummarizeOfMtrecu()) != 0) {
            return "Mtrecu summary incorrect";
        }

        return null;
    }
}
