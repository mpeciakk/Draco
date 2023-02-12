package mpeciakk.object;

public class DracoJavaClass extends DracoObject {

    private final Class<? extends DracoObject> clazz;

    public DracoJavaClass(Class<? extends DracoObject> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends DracoObject> getClazz() {
        return clazz;
    }

    @Override
    public String toString() {
        return "DracoJavaClass{" +
                "clazz=" + clazz +
                '}';
    }
}
