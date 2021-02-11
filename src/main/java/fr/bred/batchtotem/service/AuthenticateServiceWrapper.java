package fr.bred.batchtotem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import fr.bred.batchtotem.mapper.ServiceContextMapper;
import fr.bred.bsauthentmanag.BSAuthenticationManagement;
import fr.bred.bspublishmsg.exception.ServiceException;
import fr.bred.starter.common.ServiceContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthenticateServiceWrapper {
    @Autowired
    private ServiceContextMapper serviceContextMapper;
    @Autowired
    private BSAuthenticationManagement bsAuthenticationManagementClient;

    public ServiceContext getBatchServiceContext(String transactionId) throws ServiceException, JsonProcessingException {
        log.info("Begin getBatchServiceContext");
        ServiceContext sc = serviceContextMapper.getBatchServiceContext(transactionId);
        log.info("End getBatchServiceContext");
        return sc;
    }
}
