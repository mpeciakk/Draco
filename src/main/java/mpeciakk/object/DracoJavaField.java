package mpeciakk.object;

import java.lang.reflect.Field;

public class DracoJavaField extends DracoObject {

    private final Field field;

    public DracoJavaField(Field field) {
        this.field = field;
    }

    public Field getField() {
        return field;
    }

    @Override
    public String toString() {
        return "DracoJavaField{" +
                "field=" + field +
                '}';
    }
}
