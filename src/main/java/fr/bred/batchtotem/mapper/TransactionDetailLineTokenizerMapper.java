package fr.bred.batchtotem.mapper;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.stereotype.Component;

@Component
public class TransactionDetailLineTokenizerMapper {

    public DelimitedLineTokenizer mapInputRepatriationLineTokenizer() {
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

    public DelimitedLineTokenizer mapInputAssignmentDetailLineTokenizer() {
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

    public DelimitedLineTokenizer mapInputSummarizationLineTokenizer() {
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
