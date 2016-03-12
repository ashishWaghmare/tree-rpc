package in.waghmare.core.util;

import in.waghmare.core.domain.Node;

/**
 * Created by ashishw on 12/3/16.
 */
public class TreeBuilder {

    public static Node<Integer> getRootNode() {
        Node<Integer> root = new Node<Integer>().value(1);
        root.add(new Node<Integer>().value(2));
        root.add(new Node<Integer>().value(3));
        root.add(new Node<Integer>().value(4));
        root.add(new Node<Integer>().value(5));
        return root;
    }

}
