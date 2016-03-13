import Tree
import spock.lang.Specification;

/**
 * Created by ashishw on 11/3/16.
 */
public class GraphSpec extends Specification {

    def "Test Graph Storage"() {
        given:
        Tree<String> t = new Tree();
        when:
        t.root = new Node("A").add(new Node("B").add(new Node("C")))
        then:
        t != null
    }
}
