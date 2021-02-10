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

        inputTransactionStorage.setValidationMessage(message);

        return message;
    }

    public String getSummarizationResult() {
        InputSummarization inputSummarization = inputTransactionStorage.getInputSummarizations().get(0);
        TransactionSummarization transactionSummarization = inputTransactionStorage.getTransactionSummarization();

        Integer totalInputAssignmentDetail = inputSummarization.getTotalInputAssignmentDetail() == null //
                || "".equals(inputSummarization.getTotalInputAssignmentDetail()) //
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
