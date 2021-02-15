package fr.bred.batchtotem.mapper;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.stereotype.Component;

@Component
public class TransactionDetailLineTokenizerMapper {

    public DelimitedLineTokenizer mapInputRepatriationLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(";");
        lineTokenizer.setStrict(false);

     // @formatter:off
        lineTokenizer.setNames("type",
                               "countryCode",
                               "numberOfRecord",
                               "correspondingStobi",
                               "compte",
                               "refCorresponding",
                               "refDebit",
                               "currency",
                               "amountRap",
                               "euroAmount",
                               "costRap");
     // @formatter:on

        return lineTokenizer;
    }

    public DelimitedLineTokenizer mapInputAssignmentDetailLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(";");
        lineTokenizer.setStrict(false);

     // @formatter:off
        lineTokenizer.setNames("type",
                               "beneficiaryCode",
                               "customerCode",
                               "stobiNumber",
                               "amountSent",
                               "amountReceived",
                               "initialPaymentDate",
                               "patternDcu",
                               "patternRap",
                               "",
                               "");
     // @formatter:on

        return lineTokenizer;
    }

    public DelimitedLineTokenizer mapTransactionDetailLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setStrict(false);

     // @formatter:off
        lineTokenizer.setNames("type");
     // @formatter:on

        return lineTokenizer;
    }
}
