package fr.bred.batchtotem.mapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import fr.bred.bspublishmsg.MessageFormat;
import fr.bred.bspublishmsg.MessageType;
import fr.bred.bspublishmsg.PublishMessage;
import fr.bred.bspublishmsg.Recipient;
import fr.bred.bspublishmsg.RecipientPriority;

@Component
public class PublishMessageMapper {
    public PublishMessage mapEmailPublishMessage(String message) {
        PublishMessage publishMessage = new PublishMessage();

        publishMessage.setFormat(MessageFormat.V);
        publishMessage.setType(MessageType.M);
        publishMessage.setTo("wittakarn.keeratichayakorn@bred-it.com");
        publishMessage.setFrom("wittakarn.keeratichayakorn@bred-it.com");
        publishMessage.setAdditional(mapEmailRecipient());

        Map<String, String> parameters = new HashMap<>();
        parameters.put("MESSAGE", message);
        publishMessage.setParameters(parameters);

        return publishMessage;
    }

    public List<Recipient> mapEmailRecipient() {
        List<String> cc = Arrays.asList("wittakarn.expert@gmail.com");

        return cc.stream().map(c -> {
            Recipient recipient = new Recipient();
            recipient.setAddress(c);
            recipient.setPriority(RecipientPriority.CC);
            return recipient;
        }).collect(Collectors.toList());
    }
}
