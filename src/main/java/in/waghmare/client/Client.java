package in.waghmare.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by ashishw on 29/2/16.
 */
@Component
public class Client {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void outgoing(String message) {
        this.jmsTemplate.convertAndSend("incoming", "hello");
    }
}
