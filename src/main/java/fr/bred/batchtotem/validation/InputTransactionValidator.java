package fr.bred.batchtotem.validation;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.bred.batchtotem.domain.InputSummarization;
import fr.bred.batchtotem.domain.TransactionSummarization;
import fr.bred.batchtotem.storage.InputTransactionStorage;
import fr.bred.batchtotem.util.NumberUtil;

@Component
public class InputTransactionValidator {

    @Autowired
    private InputTransactionStorage inputTransactionStorage;

    public String getValidateMessage() {
        String message = null;

        if (inputTransactionStorage.getInputRepatriations().size() > 1) {
            message = "Record 1 has more then one line";
        } else if (inputTransactionStorage.getInputSummarizations().size() > 1) {
            message = "Record 3 has more then one line";
        } else {
            message = getSummarizationResult();
        }

        inputTransactionStorage.addValidationMessage(message);

        return message;
    }

    public String getSummarizationResult() {
        InputSummarization inputSummarization = inputTransactionStorage.getInputSummarizations().get(0);
        TransactionSummarization transactionSummarization = inputTransactionStorage.getTransactionSummarization();

        Integer totalInputAssignmentDetail = 0;
        if (NumberUtil.isNumeric(inputSummarization.getTotalInputAssignmentDetail())) {
            totalInputAssignmentDetail = Integer.parseInt(inputSummarization.getTotalInputAssignmentDetail());
        } else {
            return "Cannot parse TotalInputAssignmentDetail: " + inputSummarization.getTotalInputAssignmentDetail() + " to numeric";
        }

        BigDecimal summarizeOfMtemis;
        if (NumberUtil.isNumeric(inputSummarization.getSummarizeOfMtemis())) {
            summarizeOfMtemis = NumberUtil.getBigDecimalValue(inputSummarization.getSummarizeOfMtemis());
        } else {
            return "Cannot parse SummarizeOfMtemis: " + inputSummarization.getSummarizeOfMtemis() + " to numeric";
        }

        BigDecimal summarizeOfMtrecu;
        if (NumberUtil.isNumeric(inputSummarization.getSummarizeOfMtemis())) {
            summarizeOfMtrecu = NumberUtil.getBigDecimalValue(inputSummarization.getSummarizeOfMtrecu());
        } else {
            return "Cannot parse SummarizeOfMtrecu: " + inputSummarization.getSummarizeOfMtrecu() + " to numeric";
        }

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
