package com.tsystems.javaschool.tasks.calculator;

import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

    private Calculator calc = new Calculator();

    @Test
    public void evaluate() throws CalculatorParseException {
        //given
        String input = "2+3";
        String expectedResult = "5";

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate1() throws CalculatorParseException {
        //given
        String input = "4-6";
        String expectedResult = "-2";

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate2() throws CalculatorParseException {
        //given
        String input = "2*3";
        String expectedResult = "6";

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate3() throws CalculatorParseException {
        //given
        String input = "12/3";
        String expectedResult = "4";

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate4() throws CalculatorParseException {
        //given
        String input = "2+3*4";
        String expectedResult = "14";

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate5() throws CalculatorParseException {
        //given
        String input = "10/2-7+3*4";
        String expectedResult = "10";

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate6() throws CalculatorParseException {
        //given
        String input = "10/(2-7+3)*4";
        String expectedResult = "-20";

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate7() throws CalculatorParseException {
        //given
        String input = "22/3*3.0480";
        String expectedResult = "22.352";

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate8() throws CalculatorParseException {
        //given
        String input = "22/4*2.159";
        String expectedResult = "11.8745";

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate9() throws CalculatorParseException {
        //given
        String input = "22/4*2,159";
        String expectedResult = null;

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate10() throws CalculatorParseException {
        //given
        String input = "- 12)1//(";
        String expectedResult = null;

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate11() throws CalculatorParseException {
        //given
        String input = "10/(5-5)";
        String expectedResult = null;

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate12() throws CalculatorParseException {
        //given
        String input = null;
        String expectedResult = null;

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate13() throws CalculatorParseException {
        //given
        String input = "(12*(5-1)";
        String expectedResult = null;

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate14() throws CalculatorParseException {
        //given
        String input = "";
        String expectedResult = null;

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate15() throws CalculatorParseException {
        //given
        String input = "5+41..1-6";
        String expectedResult = null;

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate16() throws CalculatorParseException {
        //given
        String input = "5++41-6";
        String expectedResult = null;

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate17() throws CalculatorParseException {
        //given
        String input = "5--41-6";
        String expectedResult = null;

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate18() throws CalculatorParseException {
        //given
        String input = "5**41-6";
        String expectedResult = null;

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate19() throws CalculatorParseException {
        //given
        String input = "5//41-6";
        String expectedResult = null;

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }

}