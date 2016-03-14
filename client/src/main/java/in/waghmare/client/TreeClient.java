package in.waghmare.client;

import in.waghmare.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * Created by ashishw on 14/3/16.
 */
@Component
public class TreeClient {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private TreeNodesQueue result;

    @Autowired
    private ConnectionFactory jmsFactory;

    @Scheduled(fixedDelay = 1000L)
    public void sendRequest() throws JMSException {
        Connection connection = jmsFactory.createConnection();
        connection.start();
        Session session = connection.createSession();
        TemporaryQueue temporaryQueue = session.createTemporaryQueue();
        MessageConsumer consumer = session.createConsumer(temporaryQueue);
        String queueName = temporaryQueue.getQueueName();
        consumer.setMessageListener(new ResponseConsumer(queueName));
        Request request = new Request();
        request.setReplyTo(queueName);
        jmsTemplate.send("graph", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage objectMessage = session.createObjectMessage();
                objectMessage.setObject(request);
                return objectMessage;
            }
        });
    }


}
