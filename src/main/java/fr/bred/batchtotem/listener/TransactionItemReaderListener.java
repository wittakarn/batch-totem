package fr.bred.batchtotem.listener;

import org.springframework.batch.core.ItemReadListener;

import fr.bred.batchtotem.domain.TransactionDetail;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TransactionItemReaderListener implements ItemReadListener<TransactionDetail> {

    @Override
    public void beforeRead() {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterRead(TransactionDetail item) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onReadError(Exception ex) {
        log.error("Unexpected error", ex);
    }

}
