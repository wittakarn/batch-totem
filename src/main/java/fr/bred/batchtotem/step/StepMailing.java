package fr.bred.batchtotem.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StepMailing {
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private TaskletEmailNotifier taskletEmailNotifier;

    public Step getStep() {
        return stepBuilderFactory.get("StepMailing").tasklet(taskletEmailNotifier).build();
    }
}
