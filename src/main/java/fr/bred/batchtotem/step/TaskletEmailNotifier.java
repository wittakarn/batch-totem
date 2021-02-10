package fr.bred.batchtotem.step;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.bred.batchtotem.storage.InputTransactionStorage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TaskletEmailNotifier implements Tasklet {

    @Autowired
    private InputTransactionStorage inputTransactionStorage;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("--------------------------- Send error notification by mail ---------------------------");
        log.info(inputTransactionStorage.getValidationMessage());
        return RepeatStatus.FINISHED;
    }

}
