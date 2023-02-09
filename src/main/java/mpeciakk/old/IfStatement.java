package mpeciakk.old;

import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.expression.Expression;

import java.util.Map;

public class IfStatement extends Expression {

    private final Expression condition;
    private final Expression statement;
    private final Map<Expression, Expression> elseIfBranches;
    private final Expression elseStatement;

    public IfStatement(Expression condition, Expression statement, Map<Expression, Expression> elseIfBranches, Expression elseStatement) {
        this.condition = condition;
        this.statement = statement;
        this.elseIfBranches = elseIfBranches;
        this.elseStatement = elseStatement;
    }

    @Override
    public Object evaluate(DracoInterpreter interpreter) {
        if (isTrue(condition.evaluate(interpreter))) {
            return statement.evaluate(interpreter);
        } else {
            if (elseIfBranches != null) {
                var branches = elseIfBranches.entrySet();
                for (var branch : branches) {
                    if (isTrue(branch.getKey().evaluate(interpreter))) {
                        return branch.getValue().evaluate(interpreter);
                    }
                }
            }

            if (elseStatement != null) {
                return elseStatement.evaluate(interpreter);
            }
        }

        return null;
    }

    public static boolean isTrue(Object object) {
        if (object == null) {
            return false;
        }

        if (object instanceof Boolean bool) {
            return bool;
        }

        return true;
    }

    @Override
    public String toString() {
        return "IfStatement{" +
                "condition=" + condition +
                ", statement=" + statement +
                ", elseIfBranches=" + elseIfBranches +
                ", elseStatement=" + elseStatement +
                '}';
    }
}
