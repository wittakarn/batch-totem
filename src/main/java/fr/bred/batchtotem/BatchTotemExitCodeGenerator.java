package fr.bred.batchtotem;

import org.springframework.batch.core.JobExecution;
import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.autoconfigure.batch.JobExecutionEvent;
import org.springframework.context.ApplicationListener;

public class BatchTotemExitCodeGenerator implements ExitCodeGenerator, ExitCodeExceptionMapper, ApplicationListener<JobExecutionEvent> {

    private JobExecution jobExecution;

    @Override
    public void onApplicationEvent(JobExecutionEvent event) {
        this.jobExecution = event.getJobExecution();
    }

    @Override
    public int getExitCode() {
        return jobExecution.getAllFailureExceptions().stream()
                .mapToInt(this::getExitCode)
                .max()
                .orElse(0);
    }

    @Override
    public int getExitCode(Throwable exception) {
        if (exception != null) {
            if (exception instanceof ExitCodeGenerator) {
                return ((ExitCodeGenerator) exception).getExitCode();
            } else {
                return getExitCode(exception.getCause());
            }
        }
        return 1;
    }

}
