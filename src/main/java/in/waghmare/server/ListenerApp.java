package in.waghmare.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;

/**
 * Created by ashishw on 28/2/16.
 */
@SpringBootApplication
@EnableJms
public class ListenerApp {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public DefaultMessageListenerContainer messageListener() {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(this.connectionFactory);
        container.setDestinationName("testQueue");
        container.setMessageListener(new Listener());
        return container;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ListenerApp.class, args);
    }
}
