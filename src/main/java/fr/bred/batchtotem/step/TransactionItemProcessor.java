package fr.bred.batchtotem.step;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.bred.batchtotem.domain.InputAssignmentDetail;
import fr.bred.batchtotem.domain.InputRepatriation;
import fr.bred.batchtotem.domain.InputSummarization;
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
            inputTransactionStorage.updateTransactionSummary(inputAssignmentDetail.getMtemis(), inputAssignmentDetail.getMtrecu());

            raw.setCol1(inputAssignmentDetail.getType());
            raw.setCol2(inputAssignmentDetail.getMatricule());
            raw.setCol3(inputAssignmentDetail.getCodeCaisse());
            raw.setCol4(inputAssignmentDetail.getNDossierStobi());
            raw.setCol5(inputAssignmentDetail.getMtemis());
            raw.setCol6(inputAssignmentDetail.getMtrecu());
            raw.setCol7(inputAssignmentDetail.getDateDePaiementInitial());
            raw.setCol8(inputAssignmentDetail.getMotif());
            raw.setCol9(inputAssignmentDetail.getMotifDurap());
            raw.setCol10("");
            raw.setCol11("");
        } else if (transactionDetail instanceof InputSummarization) {
            InputSummarization inputSummarization = (InputSummarization) transactionDetail;
            inputTransactionStorage.addInputSummarization((InputSummarization) transactionDetail);

            raw.setCol1(inputSummarization.getType());
            raw.setCol2(inputSummarization.getTotalInputAssignmentDetail());
            raw.setCol3("");
            raw.setCol4("");
            raw.setCol5(inputSummarization.getSummarizeOfMtemis());
            raw.setCol6(inputSummarization.getSummarizeOfMtrecu());
            raw.setCol7("");
            raw.setCol8("");
            raw.setCol9("");
            raw.setCol10("");
            raw.setCol11("");
        } else {
            InputRepatriation inputRepatriation = (InputRepatriation) transactionDetail;
            inputTransactionStorage.addInputRepatriation(inputRepatriation);

            raw.setCol1(inputRepatriation.getType());
            raw.setCol2(inputRepatriation.getCodePays());
            raw.setCol3(inputRepatriation.getNbRap());
            raw.setCol4(inputRepatriation.getCorrStobi());
            raw.setCol5(inputRepatriation.getCompte());
            raw.setCol6(inputRepatriation.getRefCor());
            raw.setCol7(inputRepatriation.getRefDebit());
            raw.setCol8(inputRepatriation.getDevise());
            raw.setCol9(inputRepatriation.getMtRap());
            raw.setCol10(inputRepatriation.getMtEur());
            raw.setCol11(inputRepatriation.getFraisRap());
        }

        return raw;
    }

}
