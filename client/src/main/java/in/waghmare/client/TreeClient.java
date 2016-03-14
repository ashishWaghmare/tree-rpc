package in.waghmare.client;

import in.waghmare.Request;
import in.waghmare.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.lang.management.ManagementFactory;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by ashishw on 14/3/16.
 */
@Component
public class TreeClient implements MessageListener {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private TreeNodesQueue result;

    @Autowired
    private ConnectionFactory jmsFactory;

    private String clientName = ManagementFactory.getRuntimeMXBean().getName();

    private ArrayBlockingQueue<UUID> fifo = new ArrayBlockingQueue<>(20);

    @Scheduled(fixedDelay = 1000L)
    public void sendRequest() throws JMSException {
        Connection connection = jmsFactory.createConnection();
        connection.start();
        Session session = connection.createSession();
        TemporaryQueue temporaryQueue = session.createTemporaryQueue();
        MessageConsumer consumer = session.createConsumer(temporaryQueue);
        String queueName = temporaryQueue.getQueueName();
        consumer.setMessageListener(this);
        Request request = new Request();
        request.setReplyTo(queueName);
        request.setId(fifo.poll());
        request.setClientId(clientName);

        jmsTemplate.send("graph", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage objectMessage = session.createObjectMessage();
                objectMessage.setObject(request);
                return objectMessage;
            }
        });
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            Object value = null;
            try {
                value = ((ObjectMessage) message).getObject();
            } catch (JMSException e) {
                e.printStackTrace();
            }
            if (value instanceof Response) {
                Response response = (Response) value;
                System.out.println("Response :" + response.getValue());
                response.getChilds().forEach(child -> fifo.add(child));
            }
            //else ignore it
        }

    }


}
