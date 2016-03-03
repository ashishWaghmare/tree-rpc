package in.waghmare.integration.jms;

import in.waghmare.integration.jms.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 * Created by ashishw on 28/2/16.
 */
@Component
public class TimeInvoker {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Client client;


    @PostConstruct
    public void start() {
        client.start();
    }

    @Scheduled(fixedDelay = 100L)
    public void send() {
        client.triggerRequest();
    }

}