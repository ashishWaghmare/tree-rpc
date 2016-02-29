package in.waghmare.core.service;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.io.Serializable;

/**
 * Created by ashishw on 29/2/16.
 */
public class Visit implements Serializable {
    private String value;

    public Visit(String value) {
        this.value = value;

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
