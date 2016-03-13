package in.waghmare.core.event;


import lombok.Data;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by ashishw on 29/2/16.
 */

public class Request implements Serializable {

    private UUID id;
    private String clientId;

    public Request() {
    }

    public Request(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
