package fr.bred.batchtotem.mapper;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.stereotype.Component;

import fr.bred.batchtotem.domain.InputAssignmentDetail;
import fr.bred.batchtotem.domain.InputRepatriation;
import fr.bred.batchtotem.domain.InputSummarization;

@Component
public class TransactionDetailFieldSetMapper {

    public BeanWrapperFieldSetMapper<InputRepatriation> mapInputRepatriationFieldSet() {
        BeanWrapperFieldSetMapper<InputRepatriation> mapper = new BeanWrapperFieldSetMapper<>();
        mapper.setTargetType(InputRepatriation.class);
        mapper.setDistanceLimit(1);
        return mapper;
    }

    public BeanWrapperFieldSetMapper<InputAssignmentDetail> mapInputAssignmentDetailFieldSet() {
        BeanWrapperFieldSetMapper<InputAssignmentDetail> mapper = new BeanWrapperFieldSetMapper<>();
        mapper.setTargetType(InputAssignmentDetail.class);
        mapper.setStrict(false);
        mapper.setDistanceLimit(1);
        return mapper;
    }

    public BeanWrapperFieldSetMapper<InputSummarization> mapInputSummarizationFieldSet() {
        BeanWrapperFieldSetMapper<InputSummarization> mapper = new BeanWrapperFieldSetMapper<>();
        mapper.setTargetType(InputSummarization.class);
        mapper.setStrict(false);
        mapper.setDistanceLimit(1);
        return mapper;
    }
}
