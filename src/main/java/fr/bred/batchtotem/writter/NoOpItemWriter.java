package fr.bred.batchtotem.writter;

import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;

public class NoOpItemWriter<T> implements ItemStreamWriter<T> {
    @Override
    public void write(List items) throws Exception {
        // Do nothing
    }

    @Override
    public void open(ExecutionContext executionContext) {
        // Do nothing
    }

    @Override
    public void update(ExecutionContext executionContext) {
        // Do nothing
    }

    @Override
    public void close() throws ItemStreamException {
        // Do nothing
    }
}
