package mpeciakk.object;

import java.util.ArrayList;
import java.util.List;

public class DracoArray extends DracoObject {
    private final List<DracoObject> elements;

    public DracoArray(List<DracoObject> elements) {
        this.elements = elements;

        getProperties().put("size", new DracoNumber(elements.size()));
    }

    public List<DracoObject> getElements() {
        return elements;
    }

    @Override
    public String toString() {
        return "DracoArray{" +
                "elements=" + elements +
                '}';
    }
}
