package in.waghmare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by ashishw on 14/3/16.
 */
@SpringBootApplication
@EnableJms
@EnableScheduling
public class ClientMain {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ClientMain.class, args);
    }
}
