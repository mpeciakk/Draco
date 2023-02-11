package mpeciakk.parser.syntax;

import mpeciakk.parser.DracoParser;

import java.lang.reflect.InvocationTargetException;

public abstract class DracoSyntax<T extends DracoSyntax<T>> {

    public abstract boolean match(DracoParser parser);

    public abstract void parse(DracoParser parser);

    public T clone() {
        Class<T> clazz = (Class<T>) this.getClass();

        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
