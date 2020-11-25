package com.tsystems.javaschool.tasks.calculator;

import java.util.*;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */

    private static Set<String> operatorSet= new HashSet<>();
    private  static Set<String> delimiterSet= new HashSet<>();
    private static  Map<String,Integer> priorityMap=new HashMap<>();
    static {
        priorityMap.put("(", 1);    operatorSet.add("-");      delimiterSet.add("(");
        priorityMap.put("-", 2);    operatorSet.add("+");      delimiterSet.add(")");
        priorityMap.put("+", 2);    operatorSet.add("*");      delimiterSet.add(" ");
        priorityMap.put("*", 3);    operatorSet.add("/");
        priorityMap.put("/", 3);
    }

    String delimiters = " ()";
    String operators = "-+*/";

    private static int priority(String token) throws CalculatorParseException{
        int priority = priorityMap.get(token);
        if (token == null)
            throw new CalculatorParseException("wrong priority item");
        return priority;
    }

    public String evaluate(String statement) {
        String result;
        if (statement == null || statement == "")
            return null;
        try {
            List<String> list=separator(statement);
            Double doubleResult = calculation(list);
            if (doubleResult == Math.floor(doubleResult)) {
                result = String.valueOf(doubleResult.intValue());
            } else {
                result = String.valueOf(doubleResult);
            }
        } catch (CalculateException e) {
            System.out.println(e.getMessage());
            return null;
        }catch (CalculatorParseException e){
            System.out.println(e.getMessage());
            return null;
        }

        return result;

    }

    public List<String> separator(String message) throws CalculatorParseException {
        List<String> elements = new ArrayList<>();
        Deque<String> deque = new ArrayDeque<>();
        String element="";
        String prevElement="";
        StringTokenizer stringTokenizer=new StringTokenizer(message, delimiters+operators,true);
        while (stringTokenizer.hasMoreTokens()) {
            element = stringTokenizer.nextToken();
            switch (findToken(element)) {
                case SPACE:
                    continue;

                case OPERAND:
                    elements.add(element);
                    break;

                case DELIMITER:
                    if (element.equals("(")){
                        deque.push(element);
                    } else if (element.equals(")")) {
                        while (!deque.peek().equals("(")) {
                            elements.add(deque.pop());
                            if (deque.isEmpty())
                                throw new CalculatorParseException("Parentheses unpaired");
                        }
                        deque.pop();
                    }
                    break;

                case OPERATOR:
                    if (!stringTokenizer.hasMoreTokens())
                        throw new CalculatorParseException("You can not finish expression using operator");
                    if (element.equals("-") && (prevElement.equals("") || (delimiterSet.contains(prevElement)  && !prevElement.equals(")")))) {
                        elements.add("0");
                    }else{
                        while (!deque.isEmpty() && (priority(element) <= priority(deque.peek()))) {
                            elements.add(deque.pop());
                        }
                    }
                    deque.push(element);
                    break;

            }
            if (operatorSet.contains(element) && operatorSet.contains(prevElement))
                throw new CalculatorParseException("Two operators in a row");
            prevElement = element;

        }
        while (!deque.isEmpty()) {
            if (operatorSet.contains(deque.peek()))
                elements.add(deque.pop());
            else {
                throw new CalculatorParseException("Parentheses unpaired");
            }
        }
        return elements;
    }
    public Double calculation(List<String> elements) throws CalculateException {
        Deque<Double> deque = new ArrayDeque<Double>();
        Double a, b;
        for (String x : elements) {
            switch (x) {
                case "+":
                    deque.push(deque.pop() + deque.pop());
                    break;

                case "-":
                    b = deque.pop();
                    a = deque.pop();
                    deque.push(a - b);
                    break;

                case "*":
                    deque.push(deque.pop() * deque.pop());
                    break;

                case "/":
                    b = deque.pop();
                    if (b == 0)
                        throw new CalculateException("division by null");
                    a = deque.pop();
                    deque.push(a / b);
                    break;

                default:
                    try {
                        deque.push(Double.parseDouble(x));
                        break;
                    } catch (NumberFormatException e) {
                        throw new CalculateException("Element parse error");
                    }
            }
        }
        return deque.pop();
    }


    private TokenType findToken(String token){

        if (token.equals(" "))
            return TokenType.SPACE;
        if(operatorSet.contains(token))
            return TokenType.OPERATOR;
        if(delimiterSet.contains(token))
            return TokenType.DELIMITER;
        return TokenType.OPERAND;
    }
    private enum TokenType{
        SPACE,OPERAND,OPERATOR,DELIMITER
    }

}
