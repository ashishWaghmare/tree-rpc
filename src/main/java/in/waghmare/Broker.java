package in.waghmare;

import org.hornetq.core.server.embedded.EmbeddedHornetQ;

/**
 * Created by ashishw on 28/2/16.
 */
public class Broker {

    public static void main(String [] args){
        EmbeddedHornetQ embedded = new EmbeddedHornetQ();
        try {
            embedded.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
