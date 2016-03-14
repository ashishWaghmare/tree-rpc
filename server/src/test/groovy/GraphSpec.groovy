import in.waghmare.core.domain.Tree
import spock.lang.Specification
import in.waghmare.core.domain.Node

/**
 * Created by ashishw on 11/3/16.
 */
public class GraphSpec extends Specification {

    def "Graph creation Test"() {
        given:
        Tree<String> t = new Tree<String>();
        when:
        t.add "A", "B"
        t.add "B", "C"
        t.add "B", "C"
        t.add "A", "D"
        t.add "A", "E"
        t.add "A", "F"
        then:
        t != null
    }
}
