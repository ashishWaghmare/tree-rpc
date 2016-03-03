package in.waghmare.integration.jms.server;

import in.waghmare.core.domain.Node;
import in.waghmare.core.event.Request;
import in.waghmare.core.event.Response;
import in.waghmare.core.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ashishw on 29/2/16.
 */
@Component
public class Server {

    @Autowired
    TreeService treeService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "incoming")
    public void incoming(ObjectMessage message) throws JMSException {
        Object value = message.getObject();
        if (value instanceof Request) {
            Request request = (Request) value;
            //immediately  reply
            outgoing(request.getId());

        }
    }

    public void outgoing(UUID ref) {
        System.out.println("Received request for ::" + ref);
        Node<String> node = null;
        if (ref == null) {
            node = treeService.getValue();
        } else {
            node = treeService.getValue(ref);
        }
        if (null != node) {
            Response response = new Response();
            response.setValue(node.getValue());
            System.out.println("Server sending node ::" + node.getValue());
            List<UUID> childs = new ArrayList<>();
            node.getChilds().forEach(it -> childs.add(it.getId()));
            response.setUuid(node.getId());
            response.setChilds(childs);
            jmsTemplate.convertAndSend("outgoing", response);
        }
    }


}
