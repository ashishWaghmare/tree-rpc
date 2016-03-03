package in.waghmare.core.event;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Created by ashishw on 29/2/16.
 */
public class Response implements Serializable {

    private String value;
    private UUID uuid;
    private List<UUID> childs;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<UUID> getChilds() {
        return childs;
    }

    public void setChilds(List<UUID> childs) {
        this.childs = childs;
    }
}


