package domain;

import java.util.Stack;

/**
 * Postfix-class is used to change given language from infix to postfix form and
 * removes characters that are not symbols or operators.
 *
 * @author strajama
 */
public class Postfix {

    private String postfixString;

    /**
     * Creates new Postfix
     *
     * @param expression - language given in infix form
     */
    public Postfix(String expression) {
        this.postfixString = "";
        insertConcat(expression);
        toPostfix();
    }

    /**
     * Changes Postfix to String
     *
     * @return Postfix as a String
     */
    @Override
    public String toString() {
        return this.postfixString;
    }

    /**
     * Inserts concatenation-operator to the expression and is used when new
     * Postfix is created.
     *
     * @param expression that is given when new Postfix is created
     */
    private void insertConcat(String expression) {
        for (int i = 0; i < expression.length(); i++) {

            char token = expression.charAt(i);
            this.postfixString += token;

            if (token == '(' || token == '|') {
                continue;
            }

            if (i < expression.length() - 1) {
                char lookahead = expression.charAt(i + 1);
                if (lookahead == '*' || lookahead == '|' || lookahead == ')') {
                    continue;
                }
                this.postfixString += '·';
            }
        }
    }

    /**
     * Is called when new Postfix is created. It removes characters that are not
     * Operators or symbols and changes infix expression to postfix form.
     */
    private void toPostfix() {
        String output = "";
        Stack<Character> operatorStack = new Stack();

        for (int i = 0; i < this.postfixString.length(); i++) {
            char token = this.postfixString.charAt(i);
            Operator operator = tokenToOperator(token);
            if (operator != null) {
                if (!operatorStack.empty()) {
                    Operator oPeek = tokenToOperator(operatorStack.peek());
                    while (!operatorStack.empty() && oPeek != null && oPeek.getPrecedence() >= operator.getPrecedence()) {
                        output += operatorStack.pop();
                        if (!operatorStack.empty()) {
                            oPeek = tokenToOperator(operatorStack.peek());
                        }
                    }
                }
                operatorStack.push(token);
            } else if (token == '(' || token == ')') {
                if (token == '(') {
                    operatorStack.push(token);
                } else {
                    while (!operatorStack.empty() && operatorStack.peek() != '(') {
                        output += operatorStack.pop();
                    }
                    operatorStack.pop();
                }
            } else {
                output += token;
            }
        }
        while (!operatorStack.empty()) {
            output += operatorStack.pop();
        }
        this.postfixString = output;
    }

    /**
     * Is used to check if token is operator or not.
     *
     * @param token - character that is checked
     * @return Operator-enum if token is equal to enums symbol and null
     * otherwise
     */
    private Operator tokenToOperator(char token) {
        for (Operator o : Operator.values()) {
            if (o.getSymbol() == token) {
                return o;
            }
        }
        return null;
    }
}
