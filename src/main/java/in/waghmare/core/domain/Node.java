package in.waghmare.core.domain;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ashishw on 28/2/16.
 */
@Data
@Builder
public class Node<T> {
    UUID position;
    T value;
    List<Node> child = new ArrayList<>();
}
