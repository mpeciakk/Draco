package mpeciakk.runtime;

import mpeciakk.object.DracoObject;
import mpeciakk.parser.expression.other.CallExpression;
import mpeciakk.parser.expression.Statement;
import mpeciakk.object.DracoFunction;

import java.util.List;

public class DracoInterpreter {

    private final Environment global = new Environment();

    private Environment environment = global;

    {
        global.define("print", new DracoFunction() {
            @Override
            public DracoObject call(CallExpression parent, List<DracoObject> arguments) {
                for (DracoObject argument : arguments) {
                    System.out.println(argument);
                }

                return null;
            }

            @Override
            public int arity() {
                return 1;
            }

            @Override
            public String name() {
                return "print";
            }
        });
    }

    public final void evaluate(List<Statement> statements) {
        evaluate(statements.toArray(new Statement[0]));
    }

    public void evaluate(Statement... statements) {
        for (Statement statement : statements) {
            statement.evaluate(this);
        }
    }

    public Environment pushEnvironment() {
        Environment newEnvironment = new Environment(getEnvironment());
        setEnvironment(newEnvironment);
        return newEnvironment;
    }

    public void popEnvironment() {
        setEnvironment(getEnvironment().getParent());
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
