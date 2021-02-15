package fr.bred.batchtotem.mapper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.bred.batchtotem.domain.TransactionDetail;

@Component
public class CsvLineMapper {

    @Autowired
    private TransactionDetailLineTokenizerMapper transactionDetailLineTokenizerMapper;
    @Autowired
    private TransactionDetailFieldSetMapper transactionDetailFieldSetMapper;

    public PatternMatchingCompositeLineMapper<TransactionDetail> compositeLineMapper() {
        PatternMatchingCompositeLineMapper lineMapper = new PatternMatchingCompositeLineMapper();

        Map<String, LineTokenizer> tokenizers = new HashMap<>();
        tokenizers.put("1*", transactionDetailLineTokenizerMapper.mapInputRepatriationLineTokenizer());
        tokenizers.put("2*", transactionDetailLineTokenizerMapper.mapInputAssignmentDetailLineTokenizer());
        tokenizers.put("*", transactionDetailLineTokenizerMapper.mapTransactionDetailLineTokenizer());
        lineMapper.setTokenizers(tokenizers);

        Map<String, FieldSetMapper> mappers = new HashMap<>();
        mappers.put("1*", transactionDetailFieldSetMapper.mapInputRepatriationFieldSet());
        mappers.put("2*", transactionDetailFieldSetMapper.mapInputAssignmentDetailFieldSet());
        mappers.put("*", transactionDetailFieldSetMapper.mapTransactionDetailFieldSet());
        lineMapper.setFieldSetMappers(mappers);

        return lineMapper;
    }
}
