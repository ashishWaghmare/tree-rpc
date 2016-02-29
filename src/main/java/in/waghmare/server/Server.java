package in.waghmare.server;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by ashishw on 29/2/16.
 */
@Component
public class Server {

    @JmsListener(destination = "incoming")
    public void incoming(String message) {
        System.out.println("In Server received" + message);
    }


}
