package in.waghmare.integration.jms.client;

import in.waghmare.core.event.Response;
import in.waghmare.core.event.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.util.Queue;
import java.util.Stack;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by ashishw on 29/2/16.
 */
@Component
public class Client {

    @Autowired
    private JmsTemplate jmsTemplate;

    public ArrayBlockingQueue<UUID> fifo = new ArrayBlockingQueue<>(20);


    @JmsListener(destination = "outgoing")
    public void outgoing(ObjectMessage message) throws JMSException {
        Object value = message.getObject();
        if (value instanceof Response) {
            Response response = (Response) value;
            System.out.println("Got Value"+response.getValue());
            for (UUID child : response.getChilds()) {
                fifo.add(child);
            }
        }

        try {
            outgoing(fifo.poll(10, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            if (fifo.size() == 0) {
                start();
            } else {
                return;
            }
        }
    }

    public void start() {
        Request command = new Request();
        jmsTemplate.convertAndSend("incoming", command);
    }

    public void outgoing(UUID uuid) {
        Request command = new Request(uuid);
        jmsTemplate.convertAndSend("incoming", command);
    }
}
