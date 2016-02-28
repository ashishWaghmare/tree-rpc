package in.waghmare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by ashishw on 28/2/16.
 */
@SpringBootApplication
public class SampleHornetQApplication {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public ScheduledAnnotationBeanPostProcessor scheduledAnnotationBeanPostProcessor() {
        return new ScheduledAnnotationBeanPostProcessor();
    }

    @Bean
    public DefaultMessageListenerContainer messageListener() {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(this.connectionFactory);
        container.setDestinationName("testQueue");
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
        SpringApplication.run(SampleHornetQApplication.class, args);
    }
}
