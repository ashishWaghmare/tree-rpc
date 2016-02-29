package in.waghmare.server;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by ashishw on 28/2/16.
 */
public class Listener implements MessageListener {

    public Listener() {

    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println(message.getBody(Object.class));
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
}


