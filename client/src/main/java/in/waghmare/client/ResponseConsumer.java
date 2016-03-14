package in.waghmare.client;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by ashishw on 14/3/16.
 */
public class ResponseConsumer implements MessageListener {

    private final String queueName;

    public ResponseConsumer(String queueName) {
        this.queueName = queueName;
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("Got Message");
    }
}
