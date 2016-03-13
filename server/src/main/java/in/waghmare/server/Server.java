package in.waghmare.server;

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
    TreeService<Integer> treeService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "incoming")
    public void incoming(ObjectMessage message) throws JMSException {
        Object value = message.getObject();
        if (value instanceof Request) {
            Request request = (Request) value;
            //immediately  reply
            outgoing(request.getId(),request.getClientId());
        }
    }

    public void outgoing(UUID ref,String clientId) {
        System.out.println("Received request for ::" + ref + " from Client ::"+ clientId);
        Node<Integer> node = null;
        if (ref == null) {
            node = treeService.getValue();
        } else {
            node = treeService.getValue(ref);
        }
        if (null != node) {
            Response response = new Response();
            response.setValue(node.getValue());
            response.setClientId(clientId);
            System.out.println("Server sending node ::" + node.getValue()+ " to client ::"+clientId);
            List<UUID> childs = new ArrayList<>();
            node.getChilds().forEach(it -> childs.add(it.getId()));
            response.setUuid(node.getId());
            response.setChilds(childs);
            jmsTemplate.convertAndSend("outgoing", response);
        }
    }


}
