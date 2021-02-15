package fr.bred.batchtotem.step;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.bred.batchtotem.domain.InputAssignmentDetail;
import fr.bred.batchtotem.domain.InputRepatriation;
import fr.bred.batchtotem.domain.RawTransactionDetail;
import fr.bred.batchtotem.domain.TransactionDetail;
import fr.bred.batchtotem.storage.InputTransactionStorage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TransactionItemProcessor implements ItemProcessor<TransactionDetail, RawTransactionDetail> {

    @Autowired
    private InputTransactionStorage inputTransactionStorage;

    @Override
    public RawTransactionDetail process(final TransactionDetail transactionDetail) {
        RawTransactionDetail raw = new RawTransactionDetail();

        if (transactionDetail instanceof InputAssignmentDetail) {
            InputAssignmentDetail inputAssignmentDetail = (InputAssignmentDetail) transactionDetail;
            inputTransactionStorage.addInputAssignmentDetail(inputAssignmentDetail);

            raw.setCol1(inputAssignmentDetail.getType());
            raw.setCol2(inputAssignmentDetail.getBeneficiaryCode());
            raw.setCol3(inputAssignmentDetail.getCustomerCode());
            raw.setCol4(inputAssignmentDetail.getStobiNumber());
            raw.setCol5(inputAssignmentDetail.getAmountSent());
            raw.setCol6(inputAssignmentDetail.getAmountReceived());
            raw.setCol7(inputAssignmentDetail.getInitialPaymentDate());
            raw.setCol8(inputAssignmentDetail.getPatternDcu());
            raw.setCol9(inputAssignmentDetail.getPatternRap());
            raw.setCol10("");
            raw.setCol11("");
        } else if (transactionDetail instanceof InputRepatriation) {
            InputRepatriation inputRepatriation = (InputRepatriation) transactionDetail;
            inputTransactionStorage.addInputRepatriation(inputRepatriation);

            raw.setCol1(inputRepatriation.getType());
            raw.setCol2(inputRepatriation.getCountryCode());
            raw.setCol3(inputRepatriation.getNumberOfRecord());
            raw.setCol4(inputRepatriation.getCorrespondingStobi());
            raw.setCol5(inputRepatriation.getCompte());
            raw.setCol6(inputRepatriation.getRefCorresponding());
            raw.setCol7(inputRepatriation.getRefDebit());
            raw.setCol8(inputRepatriation.getCurrency());
            raw.setCol9(inputRepatriation.getAmountRap());
            raw.setCol10(inputRepatriation.getEuroAmount());
            raw.setCol11(inputRepatriation.getCostRap());
        } else {
            inputTransactionStorage.addInputUnknown(transactionDetail);

            raw.setCol1(transactionDetail.getType());
        }

        return raw;
    }

}
