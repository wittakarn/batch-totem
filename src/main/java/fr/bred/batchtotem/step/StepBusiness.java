package fr.bred.batchtotem.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StepBusiness {
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private TaskletBusiness taskletBusiness;

    public Step getStep() {
        return stepBuilderFactory.get("StepBusiness").tasklet(taskletBusiness).build();
    }
}
