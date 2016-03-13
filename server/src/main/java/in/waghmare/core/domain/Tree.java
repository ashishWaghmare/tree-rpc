package in.waghmare.core.domain;

import lombok.Builder;
import lombok.Data;

/**
 * Created by ashishw on 28/2/16.
 */

public class Tree<T> {

    private Node<T> root;

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }
}
