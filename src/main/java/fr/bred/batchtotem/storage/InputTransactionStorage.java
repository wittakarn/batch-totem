package fr.bred.batchtotem.storage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import fr.bred.batchtotem.domain.InputAssignmentDetail;
import fr.bred.batchtotem.domain.InputRepatriation;
import fr.bred.batchtotem.domain.TransactionDetail;
import fr.bred.batchtotem.domain.TransactionSummarization;
import fr.bred.batchtotem.util.NumberUtil;

@Component
public class InputTransactionStorage {

    private TransactionSummarization transactionSummarization;
    private List<InputRepatriation> inputRepatriations;
    private List<InputAssignmentDetail> inputAssignmentDetails;
    private List<TransactionDetail> inputUnknowns;
    private List<String> validationMessages;

    public InputTransactionStorage() {
        transactionSummarization = new TransactionSummarization();
        inputRepatriations = new ArrayList<>();
        inputAssignmentDetails = new ArrayList<>();
        inputUnknowns = new ArrayList<>();
        validationMessages = new ArrayList<>();
    }

    public void updateTransactionSummary(String summarizeOfMtemisStr, String summarizeOfMtrecuStr) {
        BigDecimal summarizeOfMtemis = BigDecimal.ZERO;
        BigDecimal summarizeOfMtrecu = BigDecimal.ZERO;

        if (NumberUtil.isNumeric(summarizeOfMtemisStr)) {
            summarizeOfMtemis = NumberUtil.getBigDecimalValue(summarizeOfMtemisStr);
        } else {
            addValidationMessage("Cannot parse mtemis: " + summarizeOfMtemisStr + " to numeric");
        }

        if (NumberUtil.isNumeric(summarizeOfMtrecuStr)) {
            summarizeOfMtrecu = NumberUtil.getBigDecimalValue(summarizeOfMtrecuStr);
        } else {
            addValidationMessage("Cannot parse mtrecu:  " + summarizeOfMtrecuStr + " to numeric");
        }

        transactionSummarization.setRecordCount(transactionSummarization.getRecordCount() + 1);
        transactionSummarization.setSummarizeOfMtemis(transactionSummarization.getSummarizeOfMtemis().add(summarizeOfMtemis));
        transactionSummarization.setSummarizeOfMtrecu(transactionSummarization.getSummarizeOfMtrecu().add(summarizeOfMtrecu));
    }

    public void addInputRepatriation(InputRepatriation inputRepatriation) {
        this.inputRepatriations.add(inputRepatriation);
    }

    public void addInputAssignmentDetail(InputAssignmentDetail inputAssignmentDetail) {
        this.inputAssignmentDetails.add(inputAssignmentDetail);
    }

    public TransactionSummarization getTransactionSummarization() {
        return transactionSummarization;
    }

    public List<InputRepatriation> getInputRepatriations() {
        return inputRepatriations;
    }

    public List<InputAssignmentDetail> getInputAssignmentDetails() {
        return inputAssignmentDetails;
    }

    public void addInputUnknown(TransactionDetail unknown) {
        this.inputUnknowns.add(unknown);
    }

    public List<TransactionDetail> getInputUnknowns() {
        return inputUnknowns;
    }

    public List<String> getValidationMessages() {
        return validationMessages;
    }

    public void addValidationMessage(String validationMessage) {
        this.validationMessages.add(validationMessage);
    }
}
