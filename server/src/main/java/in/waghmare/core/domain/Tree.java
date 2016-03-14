package in.waghmare.core.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by ashishw on 28/2/16.
 */

public class Tree<T> {
    private Node<T> root;
    private Map<UUID, Node> lookup = new HashMap<>();
    private Map<T, Node> valueLookup = new HashMap<>();

    public void add(T a, T b) {
        Node parent = get(a);
        Node child = get(b);
        parent.add(child);
        if (root == null) {
            root = parent;
        }
    }


    private Node<T> get(T a) {
        if (valueLookup.containsKey(a)) {
            return valueLookup.get(a);
        } else {
            Node valueA = new Node<T>().value(a);
            valueLookup.put(a, valueA);
            lookup.put(valueA.getId(), valueA);
            return valueA;
        }
    }


    public Node<T> getValue(UUID value) {
        if (lookup.containsKey(value)) {
            return lookup.get(value);
        }
        Node matched = recursive(value, root);
        if (null != matched) {
            lookup.put(value, matched);
        }
        return matched;
    }

    private Node<T> recursive(UUID toSerach, Node<T> node) {
        if (node.getId().equals(toSerach)) {
            return node;
        } else {
            for (Node<T> values : node.getChilds()) {
                Node matched = recursive(toSerach, values);
                if (null != matched) {
                    return matched;
                }
            }
        }
        return null;
    }


    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }
}
