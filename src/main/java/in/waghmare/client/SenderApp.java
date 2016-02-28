package in.waghmare.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;

/**
 * Created by ashishw on 28/2/16.
 */
@SpringBootApplication
@EnableJms
public class SenderApp {

    @Bean
    public ScheduledAnnotationBeanPostProcessor scheduledAnnotationBeanPostProcessor() {
        return new ScheduledAnnotationBeanPostProcessor();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SenderApp.class, args);
    }
}
