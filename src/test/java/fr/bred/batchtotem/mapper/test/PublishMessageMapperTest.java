package fr.bred.batchtotem.mapper.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.bred.batchtotem.AbstractTest;
import fr.bred.batchtotem.mapper.PublishMessageMapper;
import fr.bred.bspublishmsg.PublishMessage;
import fr.bred.bspublishmsg.RecipientPriority;

public class PublishMessageMapperTest extends AbstractTest {

    @Autowired
    private PublishMessageMapper publishMessageMapper;

    @Test
    public void shouldMapValidationFailedPublishMessageCorrectly() {
        PublishMessage publishMessage = publishMessageMapper.mapValidationFailedPublishMessage("mockMessage");

        Assert.assertEquals("automail@bred-it.com", publishMessage.getFrom());
        Assert.assertEquals("to1@bred-it.com", publishMessage.getTo());
        Assert.assertEquals(6, publishMessage.getAdditional().size());
        Assert.assertEquals("to1@bred-it.com", publishMessage.getAdditional().get(0).getAddress());
        Assert.assertEquals(RecipientPriority.TO, publishMessage.getAdditional().get(0).getPriority());
        Assert.assertEquals("cc3@bred-it.com", publishMessage.getAdditional().get(5).getAddress());
        Assert.assertEquals(RecipientPriority.CC, publishMessage.getAdditional().get(5).getPriority());
    }

}
