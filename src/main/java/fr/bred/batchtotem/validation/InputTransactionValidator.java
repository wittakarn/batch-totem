package fr.bred.batchtotem.validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.bred.batchtotem.domain.InputRepatriation;
import fr.bred.batchtotem.storage.InputTransactionStorage;
import fr.bred.batchtotem.util.NumberUtil;

@Component
public class InputTransactionValidator {

    @Autowired
    private InputTransactionStorage inputTransactionStorage;

    public List<String> getValidateMessage() {
        String message = null;

        if (inputTransactionStorage.getInputRepatriations().isEmpty()) {
            message = "Record 1 is missing";
        } else if (inputTransactionStorage.getInputRepatriations().size() > 1) {
            message = "Record 1 has more then one line";
        } else {
            message = getSummarizationResult();
        }

        if (message != null) {
            inputTransactionStorage.addValidationMessage(message);
        }

        return inputTransactionStorage.getValidationMessages();
    }

    public String getSummarizationResult() {

        InputRepatriation inputRepatriation = inputTransactionStorage.getInputRepatriations().get(0);

        Integer totalInputAssignmentDetail = 0;
        if (NumberUtil.isNumeric(inputRepatriation.getNumberOfRecord())) {
            totalInputAssignmentDetail = Integer.parseInt(inputRepatriation.getNumberOfRecord());
        } else {
            return "Cannot parse NumberOfRecord: " + inputRepatriation.getNumberOfRecord() + " to numeric";
        }

        if (totalInputAssignmentDetail.intValue() != inputTransactionStorage.getInputAssignmentDetails().size()) {
            return "Record count incorrect";
        }

        if (!inputTransactionStorage.getInputUnknowns().isEmpty()) {
            return "The file contains unexpcted records";
        }

        return null;
    }
}
