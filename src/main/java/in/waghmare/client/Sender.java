package in.waghmare.client;

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

    int i=0;

    @Scheduled(fixedDelay = 1000L)
    public void send() {
        this.jmsTemplate.convertAndSend("testQueue", "Ashish"+i++);
    }

}