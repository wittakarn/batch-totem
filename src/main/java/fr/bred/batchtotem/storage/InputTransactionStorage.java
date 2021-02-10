package fr.bred.batchtotem.storage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import fr.bred.batchtotem.domain.InputAssignmentDetail;
import fr.bred.batchtotem.domain.InputRepatriation;
import fr.bred.batchtotem.domain.InputSummarization;
import fr.bred.batchtotem.domain.TransactionSummarization;
import fr.bred.batchtotem.util.NumberUtil;

@Component
public class InputTransactionStorage {

    private TransactionSummarization transactionSummarization;
    private List<InputRepatriation> inputRepatriations;
    private List<InputAssignmentDetail> inputAssignmentDetails;
    private List<InputSummarization> inputSummarizations;
    private String validationMessage;

    public InputTransactionStorage() {
        transactionSummarization = new TransactionSummarization();
        inputRepatriations = new ArrayList<>();
        inputAssignmentDetails = new ArrayList<>();
        inputSummarizations = new ArrayList<>();
    }

    public void updateTransactionSummary(String summarizeOfMtemisStr, String summarizeOfMtrecuStr) {
        BigDecimal summarizeOfMtemis = NumberUtil.getBigDecimalValue(summarizeOfMtemisStr);
        BigDecimal summarizeOfMtrecu = NumberUtil.getBigDecimalValue(summarizeOfMtrecuStr);

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

    public void addInputSummarization(InputSummarization inputSummarization) {
        this.inputSummarizations.add(inputSummarization);
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

    public List<InputSummarization> getInputSummarizations() {
        return inputSummarizations;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }
}