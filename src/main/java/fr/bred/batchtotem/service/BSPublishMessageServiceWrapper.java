package fr.bred.batchtotem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.bred.batchtotem.mapper.PublishMessageMapper;
import fr.bred.batchtotem.mapper.ServiceContextMapper;
import fr.bred.bspublishmsg.BSPublishMessage;
import fr.bred.bspublishmsg.PublishMessageResponse;
import fr.bred.bspublishmsg.exception.ServiceException;
import fr.bred.starter.common.ServiceContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BSPublishMessageServiceWrapper {
    @Autowired
    private ServiceContextMapper serviceContextMapper;
    @Autowired
    private BSPublishMessage bsPublishMessageClient;
    @Autowired
    private PublishMessageMapper publishMessageMapper;

    public PublishMessageResponse sendEmail(String transactionId, String message) throws ServiceException, JsonProcessingException {
        log.info("Begin getBatchServiceContext");
        ServiceContext sc = serviceContextMapper.getBatchServiceContext(transactionId);

        PublishMessageResponse response = bsPublishMessageClient.formatAndSendMessage(sc, //
                                                                                      "TOTEM_RAP", //
                                                                                      publishMessageMapper.mapValidationFailedPublishMessage(message));

        try {
            log.info("PublishMessageResponse: {}", new ObjectMapper().writeValueAsString(response));
        } catch (JsonProcessingException e) {
            log.warn("Unable convert PublishMessageResponse object to JSON", e);
        }

        log.info("End getBatchServiceContext");
        return response;
    }
}
