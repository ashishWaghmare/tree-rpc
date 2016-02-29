package in.waghmare.core.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Created by ashishw on 28/2/16.
 */
@Data
@Builder
public class Tree<T> {
    Node<T> root;
}
