package in.waghmare.core.domain;

import lombok.Builder;
import lombok.Data;
import org.hornetq.utils.UUIDGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ashishw on 28/2/16.
 */

public class Node<T> {
    private UUID id = UUID.randomUUID();
    private T value;
    private List<Node<T>> childs = new ArrayList<>();

    public Node(){

    }
    public Node(T value) {
        this.value = value;
    }


    public Node<T> value(T b) {
        value = b;
        return this;
    }

    public Node<T> add(Node<T> child) {
        childs.add(child);
        return this;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public List<Node<T>> getChilds() {
        return childs;
    }

    public void setChilds(List<Node<T>> childs) {
        this.childs = childs;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
