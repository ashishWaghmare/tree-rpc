package in.waghmare.core.event;


import java.io.Serializable;
import java.util.UUID;

/**
 * Created by ashishw on 29/2/16.
 */

public class Request implements Serializable {

    private UUID id;
    private String clientId;
    private String replyTo;

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

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }
}
