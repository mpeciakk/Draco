package mpeciakk.object;

import java.util.HashMap;
import java.util.Map;

public class DracoObject {
    private final Map<String, DracoObject> properties = new HashMap<>();

    public Map<String, DracoObject> getProperties() {
        return properties;
    }
}
