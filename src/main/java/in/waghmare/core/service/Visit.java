package in.waghmare.core.service;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

/**
 * Created by ashishw on 29/2/16.
 */
public class Visit implements Message<String> {
    @Override
    public String toString() {
        return "visit";
    }

    @Override
    public String getPayload() {
        return "visit";
    }

    @Override
    public MessageHeaders getHeaders() {
        return null;
    }
}
