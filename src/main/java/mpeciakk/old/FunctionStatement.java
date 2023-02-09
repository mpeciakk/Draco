package mpeciakk.old;

import mpeciakk.runtime.type.DracoFunction;
import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.runtime.Environment;
import mpeciakk.expression.other.CallExpression;
import mpeciakk.expression.Expression;

import java.util.List;
import java.util.Objects;

public class FunctionStatement extends Expression {

    private final String name;
    private final List<Expression> body;
    private final List<String> arguments;

    public FunctionStatement(String name, List<Expression> body, List<String> arguments) {
        this.name = name;
        this.body = body;
        this.arguments = arguments;
    }

    @Override
    public Object evaluate(DracoInterpreter interpreter) {
        DracoFunction function = new DracoFunction() {
            @Override
            public Object call(CallExpression parent, List<Object> args) {
                if(arguments.size() != args.size()) {
                    throw new Error("Provided argument count does not match expected count!");
                }

                Environment env = interpreter.pushEnvironment();

                for (int i = 0; i < arguments.size(); i++) {
                    env.define(arguments.get(i), args.get(i));
                }

                for (Expression statement : body) {
                    statement.evaluate(interpreter);
                }

                interpreter.popEnvironment();

                return null;
            }

            @Override
            public int arity() {
                return arguments.size();
            }

            @Override
            public String name() {
                return name;
            }
        };

        if (!Objects.equals(name, "")) {
            interpreter.getEnvironment().define(name, function);
        }

        return function;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "FunctionStatement{" +
                "name='" + name + '\'' +
                ", body=" + body +
                ", arguments=" + arguments +
                '}';
    }
}
