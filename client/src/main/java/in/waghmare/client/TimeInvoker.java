package in.waghmare.client;

import in.waghmare.integration.jms.client.Client1;
import in.waghmare.integration.jms.client.Client2;
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
    private Client1 client1;
    @Autowired
    private Client2 client2;


    @PostConstruct
    public void start() throws InterruptedException {
        client1.start();
        Thread.sleep(100);
        client2.start();
    }

    @Scheduled(fixedDelay = 100L)
    public void send() {
        client1.triggerRequest();
        client2.triggerRequest();
    }

}