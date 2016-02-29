package in.waghmare.integration.jms.client;

import in.waghmare.core.service.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by ashishw on 29/2/16.
 */
@Component
public class Client {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void outgoing(String message) {
        Visit command = new Visit(message);
        jmsTemplate.convertAndSend("incoming",command);
    }
}
