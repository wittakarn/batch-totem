package fr.bred.batchtotem.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.bred.batchtotem.properties.EmailContextProperties;
import fr.bred.bspublishmsg.MessageFormat;
import fr.bred.bspublishmsg.MessageType;
import fr.bred.bspublishmsg.PublishMessage;
import fr.bred.bspublishmsg.Recipient;
import fr.bred.bspublishmsg.RecipientPriority;

@Component
public class PublishMessageMapper {

    @Autowired
    private EmailContextProperties emailContextProperties;

    public PublishMessage mapValidationFailedPublishMessage(String message) {
        PublishMessage publishMessage = new PublishMessage();

        publishMessage.setFormat(MessageFormat.V);
        publishMessage.setType(MessageType.M);
        publishMessage.setTo(emailContextProperties.getValidationFailed().getTo().get(0));
        publishMessage.setFrom(emailContextProperties.getValidationFailed().getFrom());

        List<Recipient> to = new ArrayList<>();
        if (emailContextProperties.getValidationFailed().getTo().size() > 1) {
            to = mapEmailRecipient(emailContextProperties.getValidationFailed().getTo(), //
                                   RecipientPriority.TO);
        }
        List<Recipient> cc = mapEmailRecipient(emailContextProperties.getValidationFailed().getCc(), //
                                               RecipientPriority.CC);

        to.addAll(cc);
        publishMessage.setAdditional(to);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("MESSAGE", message);
        publishMessage.setParameters(parameters);

        return publishMessage;
    }

    private List<Recipient> mapEmailRecipient(List<String> receivers, RecipientPriority recipientPriority) {
        return receivers.stream().map(r -> {
            Recipient recipient = new Recipient();
            recipient.setAddress(r);
            recipient.setPriority(recipientPriority);
            return recipient;
        }).collect(Collectors.toList());
    }
}
