package in.waghmare;

import org.hornetq.api.core.management.ManagementHelper;
import org.hornetq.api.jms.HornetQJMSClient;
import org.hornetq.api.jms.management.JMSManagementHelper;
import org.hornetq.api.jms.management.JMSServerControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.*;

/**
 * Created by ashishw on 12/3/16.
 */
@SpringBootApplication
public class ClientMain {

    @Autowired
    private ConnectionFactory connectionFactory;


    @Bean
    public DefaultMessageListenerContainer messageListener() throws Exception {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(this.connectionFactory);
        container.setDestinationName("test1");
        container.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    System.out.println(message.getBody(Object.class));
                } catch (JMSException ex) {
                    ex.printStackTrace();
                }
            }
        });
        return container;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ClientMain.class, args);
    }
}
