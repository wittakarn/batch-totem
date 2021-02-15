package fr.bred.batchtotem.router;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.bred.batchtotem.validation.InputTransactionValidator;

@Component
public class TransactionDataJobExecutionRouter implements JobExecutionDecider {

    @Autowired
    private InputTransactionValidator inputTransactionValidator;

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        if (inputTransactionValidator.getValidateMessage().isEmpty()) {
            return new FlowExecutionStatus("VALIDATE_SUCCESS");
        } else {
            return new FlowExecutionStatus("VALIDATE_FAILED");
        }
    }
}
