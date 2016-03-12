package in.waghmare;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.core.config.Configuration;
import org.hornetq.core.remoting.impl.netty.NettyAcceptorFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.hornetq.HornetQConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by ashishw on 12/3/16.
 */
@SpringBootApplication
public class Application {

    @Bean
    public HornetQConfigurationCustomizer hornetCustomizer() {
        return new HornetQConfigurationCustomizer() {
            @Override
            public void customize(Configuration configuration) {
                Set<TransportConfiguration> acceptors = configuration.getAcceptorConfigurations();
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("host", "localhost");
                params.put("port", "5445");
                TransportConfiguration tc = new TransportConfiguration(NettyAcceptorFactory.class.getName(), params);
                acceptors.add(tc);
            }
        };
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
