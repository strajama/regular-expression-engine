/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Stack;

/**
 *
 * @author strajama
 */
public class Postfix {

    private String postfixString;

    public Postfix(String expression) {
        this.postfixString = "";
        insertConcat(expression);
        toPostfix();
    }

    public String getPostfixString() {
        return postfixString;
    }

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

    private Operator tokenToOperator(char token) {
        for (Operator o : Operator.values()) {
            if (o.getSymbol() == token) {
                return o;
            }
        }
        return null;
    }
}

