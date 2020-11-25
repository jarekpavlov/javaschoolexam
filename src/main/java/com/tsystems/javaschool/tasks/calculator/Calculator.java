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
    static {
        operatorSet.add("-");      delimiterSet.add("(");
        operatorSet.add("+");      delimiterSet.add(")");
        operatorSet.add("*");      delimiterSet.add(" ");
        operatorSet.add("/");
    }

    String delimiters = " ()";
    String operators = "-+*/";
    public String evaluate(String statement) throws CalculatorParseException {
        List<String> elements = new ArrayList<>();
        String element;
        StringTokenizer stringTokenizer=new StringTokenizer(statement, delimiters+operators,true);
        while (stringTokenizer.hasMoreTokens()){
            element=stringTokenizer.nextToken();
            switch (findToken(element)){
                case SPACE:
                    continue;
                case OPERAND:
                    elements.add(element);
                    break;
                case DELIMITER:
                    if (element.equals("(") || (element.equals(")")))
                            elements.add(element);
                    break;
                case OPERATOR:
                    if (!stringTokenizer.hasMoreTokens())
                        throw new CalculatorParseException("You can not finish expression using operator");
                    elements.add(element);
                    break;
            }


        }

        return elements.toString();
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
