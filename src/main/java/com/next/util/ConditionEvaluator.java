package com.next.util;

import java.util.Map;
import java.util.Stack;

public class ConditionEvaluator {

    public boolean evaluate(String condition, Map<String, Object> context) {
        String parsedCondition = replaceVariables(condition, context);
        return evaluateExpression(parsedCondition);
    }

    private String replaceVariables(String condition, Map<String, Object> context) {
        for (Map.Entry<String, Object> entry : context.entrySet()) {
            String variable = entry.getKey();
            String value = entry.getValue() != null ? entry.getValue().toString() : "null";
            condition = condition.replace(variable, value);
        }
        return condition;
    }

    private boolean evaluateExpression(String expression) {
        Stack<Boolean> values = new Stack<>();
        Stack<String> operators = new Stack<>();

        // Tokenize the expression
        String[] tokens = expression.split(" ");
        for (String token : tokens) {
            token = token.trim();
            if (token.isEmpty()) continue;

            if (token.equals("true") || token.equals("false")) {
                values.push(Boolean.parseBoolean(token));
            } else if (isOperator(token)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token)) {
                    values.push(applyOperator(operators.pop(), values));
                }
                operators.push(token);
            } else if (token.equals("(")) {
                operators.push("(");
            } else if (token.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    values.push(applyOperator(operators.pop(), values));
                }
                operators.pop(); // Remove '('
            } else {
                // Handle comparison operators (e.g., ==, !=, >, etc.)
                if (isComparisonOperator(token)) {
                    values.push(evaluateComparison(token));
                }
            }
        }

        // Final evaluation
        while (!operators.isEmpty()) {
            values.push(applyOperator(operators.pop(), values));
        }

        return values.pop();
    }

    private boolean evaluateComparison(String token) {
        String[] parts;
        if (token.contains("==")) {
            parts = token.split("==");
            return parts[0].trim().equals(parts[1].trim());
        } else if (token.contains("!=")) {
            parts = token.split("!=");
            return !parts[0].trim().equals(parts[1].trim());
        } else if (token.contains(">=")) {
            parts = token.split(">=");
            return Double.parseDouble(parts[0].trim()) >= Double.parseDouble(parts[1].trim());
        } else if (token.contains("<=")) {
            parts = token.split("<=");
            return Double.parseDouble(parts[0].trim()) <= Double.parseDouble(parts[1].trim());
        } else if (token.contains(">")) {
            parts = token.split(">");
            return Double.parseDouble(parts[0].trim()) > Double.parseDouble(parts[1].trim());
        } else if (token.contains("<")) {
            parts = token.split("<");
            return Double.parseDouble(parts[0].trim()) < Double.parseDouble(parts[1].trim());
        }
        throw new IllegalArgumentException("Invalid comparison: " + token);
    }

    private boolean isOperator(String token) {
        return token.equals("&&") || token.equals("||") || token.equals("!");
    }

    private boolean isComparisonOperator(String token) {
        return token.contains("==") || token.contains("!=") ||
                token.contains(">=") || token.contains("<=") ||
                token.contains(">") || token.contains("<");
    }

    private int precedence(String operator) {
        return switch (operator) {
            case "!" -> 3;
            case "&&" -> 2;
            case "||" -> 1;
            default -> 0;
        };
    }

    private boolean applyOperator(String operator, Stack<Boolean> values) {
        if (operator.equals("!")) {
            return !values.pop();
        } else {
            boolean b = values.pop();
            boolean a = values.pop();
            return switch (operator) {
                case "&&" -> a && b;
                case "||" -> a || b;
                default -> throw new IllegalArgumentException("Invalid operator: " + operator);
            };
        }
    }
}