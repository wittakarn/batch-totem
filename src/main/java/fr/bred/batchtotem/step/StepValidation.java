package fr.bred.batchtotem.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import fr.bred.batchtotem.domain.RawTransactionDetail;
import fr.bred.batchtotem.domain.TransactionDetail;

@Component
public class StepValidation {
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private TransactionItemProcessor transactionItemProcessor;
    @Autowired
    private TransactionItemWriter transactionItemWriter;
    @Autowired
    private TransactionItemReader csvReader;
    @Autowired
    private ItemStreamWriter<RawTransactionDetail> csvSuccessWriter;
    @Autowired
    private ItemStreamWriter<RawTransactionDetail> csvFailedWriter;

    @Value("${batch.chunk.size}")
    private int chunkSize;

    public Step getStep() {
        return stepBuilderFactory.get("StepValidation") //
                .<TransactionDetail, RawTransactionDetail> chunk(chunkSize) //
                .reader(csvReader.csvInputReader()) //
                .processor(transactionItemProcessor) //
                .writer(transactionItemWriter.compositeItemWriter()) //
                .stream(csvSuccessWriter) //
                .stream(csvFailedWriter) //
                .build();
    }
}
