package in.waghmare.core.service;

import in.waghmare.core.domain.Node;
import in.waghmare.core.domain.Tree;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by ashishw on 1/3/16.
 */
@Component
public class TreeService {

    Tree<String> one;
    Map<UUID, Node> lookup = new HashMap<>();

    public TreeService() {

        Node<String> root = new Node<String>().value("a");
        one = new Tree<>();
        one.setRoot(root);

        root.add(new Node<String>().value("b"));
        root.add(new Node<String>().value("c"));
        root.add(new Node<String>().value("d"));
        root.add(new Node<String>().value("e"));
    }

    public Node<String> getValue() {
        return one.getRoot();
    }

    public Node<String> getValue(UUID value) {
        if (lookup.containsKey(value)) {
            return lookup.get(value);
        }
        Node matched = recursive(value, one.getRoot());
        if (null != matched) {
            lookup.put(value, matched);
        }
        return matched;
    }

    private Node<String> recursive(UUID toSerach, Node<String> node) {
        if (node.getId().equals(toSerach)) {
            return node;
        } else {
            for (Node<String> values : node.getChilds()) {
                Node matched = recursive(toSerach, values);
                if (null != matched) {
                    return matched;
                }
            }
        }
        return null;
    }
}
