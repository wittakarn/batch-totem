package fr.bred.batchtotem.step;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.classify.BackToBackPatternClassifier;
import org.springframework.stereotype.Component;

import fr.bred.batchtotem.domain.RawTransactionDetail;
import fr.bred.batchtotem.router.TransactionDataValidationRouter;

@Component
public class TransactionItemWriter {
    @Autowired
    private TransactionDataValidationRouter transactionDataValidationRouter;
    @Autowired
    private ItemStreamWriter<RawTransactionDetail> csvSuccessWriter;
    @Autowired
    private ItemStreamWriter<RawTransactionDetail> csvFailedWriter;

    public ClassifierCompositeItemWriter<RawTransactionDetail> compositeItemWriter() {
        ClassifierCompositeItemWriter<RawTransactionDetail> writer = new ClassifierCompositeItemWriter<>();

        Map<String, ItemWriter<? super RawTransactionDetail>> matcherMap = new HashMap<>();
        matcherMap.put("OK", csvSuccessWriter);
        matcherMap.put("KO", csvFailedWriter);

        BackToBackPatternClassifier<RawTransactionDetail, ItemWriter<? super RawTransactionDetail>> classifier = new BackToBackPatternClassifier<>();

        classifier.setRouterDelegate(transactionDataValidationRouter);
        classifier.setMatcherMap(matcherMap);

        writer.setClassifier(classifier);
        return writer;
    }
}
