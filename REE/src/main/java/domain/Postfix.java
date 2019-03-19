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

    private String postfix;

    public Postfix(String expression) {
        this.postfix = "";
        insertConcat(expression);
        System.out.println("postfix "+this.postfix);
        toPostfix();
    }

    public String getPostfix() {
        return postfix;
    }

    private void insertConcat(String expression) {
        for (int i = 0; i < expression.length(); i++) {
            char token = expression.charAt(i);
            postfix += token;

            if (token == '(' || token == '|') {
                continue;
            }

            if (i < expression.length() - 1) {
                char lookahead = expression.charAt(i + 1);
                if (lookahead == '*' || lookahead == '|' || lookahead == ')') {
                    continue;
                }
                postfix += '·';
            }
        }
    }

    private void toPostfix() {
        String output = "";
        Stack<Character> operatorStack = new Stack();

        for (int i = 0; i < this.postfix.length(); i++) {
            char token = this.postfix.charAt(i);
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
        this.postfix = output;

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

