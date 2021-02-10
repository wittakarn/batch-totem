package fr.bred.batchtotem.mapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import fr.bred.batchtotem.domain.RawTransactionDetail;
import fr.bred.batchtotem.writter.NoOpItemWriter;

@Component
public class ItemWriterMapper {

    @Value("${csv.output}")
    private String cvsOutputFolder;

    public List<ItemWriter<? super RawTransactionDetail>> mapTransactionDataItemWriter(String summarizationResult) {
        List<ItemWriter<? super RawTransactionDetail>> writers = new ArrayList<>();
        if (summarizationResult != null) {
            writers.add(csvFailedWriter());
        }

        return writers;
    }

    public ItemStreamWriter<RawTransactionDetail> csvSuccessWriter() {
        return new NoOpItemWriter<>();
    }

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
