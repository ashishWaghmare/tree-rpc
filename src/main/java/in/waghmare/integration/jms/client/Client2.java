package in.waghmare.integration.jms.client;

import in.waghmare.core.event.Request;
import in.waghmare.core.event.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by ashishw on 9/3/16.
 */
@Component
public class Client2 {

    private static final String MY_ID = "client2";

    @Autowired
    private JmsTemplate jmsTemplate;
    private ArrayBlockingQueue<UUID> fifo = new ArrayBlockingQueue<>(20);
    private Boolean started = false;

    @JmsListener(destination = "outgoing")
    public void outgoing(ObjectMessage message) throws JMSException {
        Object value = message.getObject();
        if (value instanceof Response) {
            Response response = (Response) value;
            if (MY_ID.equals(response.getClientId())) {
                response.getChilds().forEach(child -> fifo.add(child));
                System.out.println("Received for Client 2 from server::" + response.getValue());
            }
        }
    }

    public void triggerRequest() {
        try {
            outgoing(fifo.poll(10, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            if (fifo.size() == 0) {
                start();
            } else {
                outgoing(fifo.poll());
            }
        }
    }

    public void start() {
        synchronized (started) {
            if (!started) {
                Request command = new Request();
                command.setClientId(MY_ID);
                jmsTemplate.convertAndSend("incoming", command);
                started = true;
            }
        }
    }

    public void outgoing(UUID uuid) {
        Request command = new Request(uuid);
        command.setClientId(MY_ID);
        jmsTemplate.convertAndSend("incoming", command);
    }

}
