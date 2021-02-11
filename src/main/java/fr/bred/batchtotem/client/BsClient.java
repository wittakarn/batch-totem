package fr.bred.batchtotem.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.bred.bsauthentmanag.BSAuthenticationManagement;
import fr.bred.bsauthentmanag.utils.BSAuthenticationManagementErrorMapper;
import fr.bred.bspublishmsg.BSPublishMessage;
import fr.bred.bspublishmsg.utils.BSPublishMessageErrorMapper;
import fr.bred.starter.jaxrs.client.ProxyBuilder;

@Configuration
public class BsClient {
    @Bean
    public BSAuthenticationManagement bsAuthenticationManagementClient(@Value("${url.bsauthentmanag}") String bsAuthentmanagUrl) {
        return ProxyBuilder.builder() //
                .exceptionMapper(BSAuthenticationManagementErrorMapper::getServiceException) //
                .build(BSAuthenticationManagement.class, bsAuthentmanagUrl);
    }

    @Bean
    public BSPublishMessage bsPublishMessageClient(@Value("${url.bspublishmsg}") String bspublishmsg) {
        return ProxyBuilder.builder() //
                .exceptionMapper(BSPublishMessageErrorMapper::getServiceException) //
                .build(BSPublishMessage.class, bspublishmsg);
    }
}
