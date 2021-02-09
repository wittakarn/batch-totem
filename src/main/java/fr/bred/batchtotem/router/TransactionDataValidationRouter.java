package fr.bred.batchtotem.router;

import org.springframework.batch.support.annotation.Classifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.bred.batchtotem.business.TransactionBusiness;
import fr.bred.batchtotem.domain.RawTransactionDetail;

@Component
public class TransactionDataValidationRouter {

    @Autowired
    private TransactionBusiness transactionBusiness;

    @Classifier
    public String classify(RawTransactionDetail rawTransactionDetail) {
        return transactionBusiness.getSummarizationResult() == null ? "OK" : "KO";
    }
}
