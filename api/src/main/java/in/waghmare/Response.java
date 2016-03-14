package in.waghmare;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Created by ashishw on 29/2/16.
 */
public class Response implements Serializable {

    private Object value;
    private UUID uuid;

    private String clientId;
    private List<UUID> childs;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }


    public List<UUID> getChilds() {
        return childs;
    }

    public void setChilds(List<UUID> childs) {
        this.childs = childs;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}


