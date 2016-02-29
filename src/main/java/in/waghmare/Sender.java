package in.waghmare;

import in.waghmare.client.Client;
import in.waghmare.server.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * Created by ashishw on 28/2/16.
 */
@Component
public class Sender {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Client client;

    @Scheduled(fixedDelay = 1000L)
    public void send() {
        client.outgoing("hello");
    }

}