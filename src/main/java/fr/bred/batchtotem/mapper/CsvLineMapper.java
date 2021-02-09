package fr.bred.batchtotem.mapper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
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
        tokenizers.put("3*", transactionDetailLineTokenizerMapper.mapInputSummarizationLineTokenizer());
        lineMapper.setTokenizers(tokenizers);

        Map<String, FieldSetMapper> mappers = new HashMap<>();
        mappers.put("1*", transactionDetailFieldSetMapper.mapInputRepatriationFieldSet());
        mappers.put("2*", transactionDetailFieldSetMapper.mapInputAssignmentDetailFieldSet());
        mappers.put("3*", transactionDetailFieldSetMapper.mapInputSummarizationFieldSet());
        lineMapper.setFieldSetMappers(mappers);

        return lineMapper;
    }

    public DelimitedLineTokenizer inputRepatriationLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(";");

     // @formatter:off
        lineTokenizer.setNames("type",
                               "codePay",
                               "nbRap",
                               "corrStobi",
                               "compte",
                               "refCor",
                               "refDebit",
                               "devise",
                               "mtRap",
                               "meEur",
                               "fraisRap");
     // @formatter:on

        return lineTokenizer;
    }

    public DelimitedLineTokenizer inputAssignmentDetailLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(";");
        lineTokenizer.setStrict(false);

     // @formatter:off
        lineTokenizer.setNames("type",
                               "matricule",
                               "codeCaisse",
                               "nDossierStobi",
                               "mtemis",
                               "mtrecu",
                               "dateDePaiementInitial",
                               "motif",
                               "motifDurap",
                               "",
                               "");
     // @formatter:on

        return lineTokenizer;
    }

    public DelimitedLineTokenizer inputSummarizationLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(";");
        lineTokenizer.setStrict(false);

     // @formatter:off
        lineTokenizer.setNames("type",
                               "totalInputAssignmentDetail",
                               "",
                               "",
                               "summarizeOfMtemis",
                               "summarizeOfMtrecu",
                               "",
                               "",
                               "",
                               "",
                               "");
     // @formatter:on

        return lineTokenizer;
    }
}
