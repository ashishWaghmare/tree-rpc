package in.waghmare.core.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashishw on 28/2/16.
 */
public class Node<T> {
    T value;
    List<Node> child = new ArrayList<>();
}
