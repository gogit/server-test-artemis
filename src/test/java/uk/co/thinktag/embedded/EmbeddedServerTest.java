package uk.co.thinktag.embedded;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.junit.Assert;
import org.junit.Test;

public class EmbeddedServerTest {

    @Test
    public void test() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("Starting message test");
        try (final ActiveMQConnectionFactory acf =
                        new ActiveMQConnectionFactory("tcp://localhost:61616");
                        final Connection connection = acf.createConnection();) {
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            final Queue queue = session.createQueue("cakeshop.controller.command");
            MessageProducer producer = session.createProducer(queue);
            TextMessage message = session.createTextMessage("Hello sent at " + new Date());
            System.out.println("Sending message: " + message.getText());
            producer.send(message);
            MessageConsumer messageConsumer = session.createConsumer(queue);
            connection.start();
            TextMessage messageReceived = (TextMessage) messageConsumer.receive(1000);
            System.out.println("Received message:" + messageReceived.getText());
            //throw new RuntimeException("Test shutdown hook");
        } catch (JMSException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
