package fr.bred.batchtotem.step;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import fr.bred.batchtotem.domain.TransactionDetail;
import fr.bred.batchtotem.mapper.CsvLineMapper;

@Component
public class TransactionItemReader {

    @Autowired
    private CsvLineMapper csvLineMapper;

    public ItemReader<TransactionDetail> csvInputReader() {
        return new FlatFileItemReaderBuilder<TransactionDetail>() //
                .name("csvInputReader") //
                .resource(new ClassPathResource("INPUT_FILE_TEST_v1.csv")) //
                .lineMapper(csvLineMapper.compositeLineMapper()) //
                .build();
    }
}
