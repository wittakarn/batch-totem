package fr.bred.batchtotem.config;

import java.io.File;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import fr.bred.batchtotem.BatchTotemExitCodeGenerator;
import fr.bred.batchtotem.domain.RawTransactionDetail;
import fr.bred.batchtotem.step.JobStep;
import fr.bred.batchtotem.writter.NoOpItemWriter;
import fr.bred.starter.core.EnableStarterCore;

@Configuration
@EnableStarterCore
@EnableBatchProcessing
@ComponentScan("fr.bred.batchtotem")
public class BatchTotemConfiguration {

    @Autowired
    private JobStep jobStep;

    @Value("${csv.output}")
    private String cvsOutputFolder;

    @Bean
    public ExitCodeGenerator exitCodeGenerator() {
        return new BatchTotemExitCodeGenerator();
    }

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory) {
     // @formatter:off
        return jobBuilderFactory.get("batch-totem")
                .incrementer(new RunIdIncrementer())
                .flow(jobStep.stepValidateData())
                .end()
                .build();
     // @formatter:on
    }

    @Bean
    @StepScope
    public ItemStreamWriter<RawTransactionDetail> csvSuccessWriter() {
        return new NoOpItemWriter<>();
    }

    @Bean
    @StepScope
    public ItemStreamWriter<RawTransactionDetail> csvFailedWriter() {
        return new FlatFileItemWriterBuilder<RawTransactionDetail>() //
                .name("csvFailedWriter") //
                .resource(new FileSystemResource(new File(cvsOutputFolder + "failed.csv"))) //
                .delimited() //
                .delimiter(";") //
                .names("col1", "col2", "col3", "col4", "col5", "col6", "col7", "col8", "col9", "col10", "col11") //
                .build();
    }
}
