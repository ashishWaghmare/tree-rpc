package in.waghmare.core.service;

import in.waghmare.core.domain.Node;
import in.waghmare.core.domain.Tree;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by ashishw on 1/3/16.
 */
@Component
public class TreeService<T> {
    Tree<T> singleTree = new Tree<>();

    public TreeService() {
    }

    public Node<T> getValue() {
        return singleTree.getRoot();
    }

    public Node<T> getValue(UUID id) {
        return singleTree.getValue(id);
    }


    public void buildTree(T parent, T... childs) {
        for (T child : childs) {
            singleTree.add(parent, child);
        }
    }
}
