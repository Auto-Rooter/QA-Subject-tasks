package main.java.exercise1;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.logging.Logger;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

public class calculatorTest {
    calculator calc =  new calculator();

    @Test
    public void emptyStringHasZero(){
        calc.add("");
        assertEquals(0, calc.getSum());
    }

    @Test
    public void stringWithOneNum(){
        calc.add("2");
        assertEquals(2, calc.getSum());
    }

    @Test
    public void stringUpToTwoNumsSeparatedByCommas(){
        calc.add("2,4");
        assertEquals(6, calc.getSum());
    }

    @Test
    public void stringWithUnknownAmountOfNumber(){
        calc.add("3,4,5,6,7,1,2,1");
        assertEquals(29, calc.getSum());
    }

    @Test
    public void numbersSeparatedByNewLines(){
        calc.add("3\n5\n1\n1");
        assertEquals(10, calc.getSum());
    }

    @Test
    public void stringContainsNegativeNumsThrowErrors(){
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> calc.add("//;\n1;-2;4;-5"),
                "Expected: Negatives Not Allowed Error "
        );
        assertTrue(thrown.getMessage().contains("Negatives Not Allowed: -2 -5"));
    }

    @Test
    public void numberBiggerThan1000Ignored(){
        calc.add("//;\n1;2;40000;2;1000");
        assertEquals(1005, calc.getSum());
    }

    @Test
    public void delimitersLengthCanBeAnyNumber(){
        calc.add("//****\n1****5****3");
        assertEquals(9, calc.getSum());
    }

    @Test
    public void multibleDelimitersAllowed(){
        calc.add("//*%\n1*2%3");
        assertEquals(6, calc.getSum());
    }

    @Test
    public void multibleDelimitersWithMultiLengthAllowed(){
        calc.add("//***%\n1***2%3");
        assertEquals(6, calc.getSum());
    }


}