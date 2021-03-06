package instruments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculator {
    // аргументы и операция представляют собой пару из их индекса в списке и значения
    private int iterator;

    public String calculateExpression(List<Operation> expression) {
        List resExpression = new ArrayList(expression);
        while (resExpression.size() != 1) {
            iterator = 0;
            resExpression = solveSimpleExpression(resExpression);
        }
        System.out.println(resExpression);
       return resExpression.toString();
    }

    private List<Operation> solveSimpleExpression(List<Operation> expression) {
        while ( (iterator + 2) < expression.size() && expression.get(iterator + 2).getPriority() == 0) {
            iterator++;
        }
        if ((iterator + 2) < expression.size()) {
            double arg1 = Double.parseDouble(expression.get(iterator).getOperation());
            iterator++;
            double arg2 = Double.parseDouble(expression.get(iterator).getOperation());
            iterator++;
            String oper = expression.get(iterator).getOperation();

            expression.remove(iterator);
            expression.remove(iterator - 1);
            expression.set(iterator - 2, new Operation(String.valueOf(doOperation(arg1, arg2, oper)), Operation.OPERAND_PRIORITY));
            return expression;
        }
        return expression;
    }

    private double doOperation(double arg1, double arg2, String oper) {
        switch (oper) {
            case "+":
                return arg1 + arg2;
            case "-":
                return arg1 - arg2;
            case "*":
                return arg1 * arg2;
            case "/":
                return arg1 / arg2;
            case "^":
                return Math.pow(arg1, arg2);
            case "log":
                return Math.log(arg1) / Math.log(arg2);
        }
        return 0;
    }
}
