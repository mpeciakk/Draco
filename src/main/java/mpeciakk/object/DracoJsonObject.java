package mpeciakk.object;

import java.util.HashMap;
import java.util.Map;

public class DracoJsonObject extends DracoObject {
    private final Map<String, DracoObject> children;

    public DracoJsonObject(Map<String, DracoObject> children) {
        this.children = children;
    }

    public Map<String, DracoObject> getChildren() {
        return children;
    }
}
