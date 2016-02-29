package in.waghmare.integration.jms.server;

import in.waghmare.core.service.Visit;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

/**
 * Created by ashishw on 29/2/16.
 */
@Component
public class Server {

    @JmsListener(destination = "incoming")
    public void incoming(ObjectMessage message) throws JMSException {
        Object value= message.getObject();
        if(value instanceof Visit) {
            System.out.println("Visit :: " +((Visit)value).getValue());
        }
    }


}
