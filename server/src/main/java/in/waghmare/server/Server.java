package in.waghmare.server;

import in.waghmare.core.domain.Node;
import in.waghmare.Request;
import in.waghmare.Response;
import in.waghmare.core.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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

    @PostConstruct
    public void init() {
        treeService.buildTree(1, 2, 3);
        treeService.buildTree(2, 7, 8);
        treeService.buildTree(3, 9, 10);
    }


    @JmsListener(destination = "graph")
    public void incoming(Message message) throws JMSException {
        Object payload=message.getPayload();
        if (payload instanceof Request) {
            Request request = (Request)payload;
            //immediately  reply
            Response response = getResponse(request.getId(), request.getClientId());
            jmsTemplate.convertAndSend(request.getReplyTo(), response);
        }
    }

    public Response getResponse(UUID ref, String clientId) {
        System.out.println("Received request for ::" + ref + " from Client ::" + clientId);
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
            System.out.println("Server sending node ::" + node.getValue() + " to client ::" + clientId);
            List<UUID> childs = new ArrayList<>();
            node.getChilds().forEach(it -> childs.add(it.getId()));
            response.setUuid(node.getId());
            response.setChilds(childs);
            return response;
        }
        return null;
    }
}
