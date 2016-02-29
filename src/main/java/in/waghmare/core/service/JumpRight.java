package in.waghmare.core.service;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

/**
 * Created by ashishw on 29/2/16.
 */
public class JumpRight implements Message<String> {
    @Override
    public String toString() {
        return "right";
    }

    @Override
    public String getPayload() {
        return "right";
    }

    @Override
    public MessageHeaders getHeaders() {
        return null;
    }
}
