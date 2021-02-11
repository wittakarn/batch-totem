package fr.bred.batchtotem.step;

import java.io.File;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import fr.bred.batchtotem.domain.TransactionDetail;
import fr.bred.batchtotem.mapper.CsvLineMapper;

@Component
public class TransactionItemReader {

    @Autowired
    private CsvLineMapper csvLineMapper;

    @Value("${csv.input}")
    private String cvsInputFolder;

    public ItemReader<TransactionDetail> csvInputReader() {
        return new FlatFileItemReaderBuilder<TransactionDetail>() //
                .name("csvInputReader") //
                .resource(new FileSystemResource(new File(cvsInputFolder + "INPUT_FILE_TEST_v1.csv"))) //
                .strict(false) //
                .lineMapper(csvLineMapper.compositeLineMapper()) //
                .build();
    }
}
