package fr.bred.batchtotem.mapper;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.bred.batchtotem.properties.BatchUserProperties;
import fr.bred.batchtotem.properties.ServiceContextProperties;
import fr.bred.starter.common.ServiceContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ServiceContextMapper {

    @Autowired
    private ServiceContextProperties properties;

    public ServiceContext getBatchServiceContext(String transactionId) {
        BatchUserProperties batchUser = properties.getBatchUser();
        ServiceContext ssoServiceContext = new ServiceContext();
        ssoServiceContext.setApplicationId(batchUser.getApplicationId());
        ssoServiceContext.setConsumingId(batchUser.getConsumingId());
        ssoServiceContext.setConsumingChannel(batchUser.getConsumingChannel());
        ssoServiceContext.setUserPeo(batchUser.getPeo());
        ssoServiceContext.setUserId(batchUser.getUserId());
        ssoServiceContext.setSocietyCode(batchUser.getSocietyCode());
        ssoServiceContext.setTransactionId(transactionId);
        ssoServiceContext.setDisableTokenValidation(true);
        try {
            ssoServiceContext.setTokenContext(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            ssoServiceContext.setTokenContext(batchUser.getTokenContext());
        }

        try {
            log.info("getDefaultServiceContext: {}", new ObjectMapper().writeValueAsString(ssoServiceContext));
        } catch (JsonProcessingException e) {
            log.warn("Unable convert ssoServiceContext object to JSON", e);
        }
        return ssoServiceContext;
    }
}
