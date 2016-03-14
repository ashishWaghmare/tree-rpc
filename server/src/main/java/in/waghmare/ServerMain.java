package in.waghmare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * Created by ashishw on 13/3/16.
 */
@SpringBootApplication
@EnableJms
public class ServerMain {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ServerMain.class, args);
    }
}
