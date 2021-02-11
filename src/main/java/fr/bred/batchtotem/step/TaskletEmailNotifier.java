package fr.bred.batchtotem.step;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.bred.batchtotem.service.BSPublishMessageServiceWrapper;
import fr.bred.batchtotem.storage.InputTransactionStorage;
import jdk.jfr.Timestamp;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TaskletEmailNotifier implements Tasklet {

    @Autowired
    private InputTransactionStorage inputTransactionStorage;
    @Autowired
    BSPublishMessageServiceWrapper bsPublishMessageServiceWrapper;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("--------------------------- Send error notification by mail ---------------------------");
        String message = inputTransactionStorage.getValidationMessages().stream() //
                .map(m -> m) //
                .reduce((acc, m) -> acc.concat(" ," + m)).orElse("");
        bsPublishMessageServiceWrapper.sendEmail(Timestamp.MILLISECONDS_SINCE_EPOCH, message);
        return RepeatStatus.FINISHED;
    }

}
