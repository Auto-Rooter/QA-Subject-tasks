package main.java.exercise2;

import org.junit.Test;

import java.util.logging.Logger;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class calculator2Test {
    calculator2 calc =  new calculator2(Logger.getLogger("main.java"), new CalcLoggingHandler());

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

//    @Test
//    public void stringContainsNegativeNumsThrowErrors(){
//        IllegalArgumentException thrown = assertThrows(
//                IllegalArgumentException.class,
//                () -> calc.add("//;\n1;-2;4;-5"),
//                "Expected: Negatives Not Allowed Error "
//        );
//        assertTrue(thrown.getMessage().contains("Negatives Not Allowed: -2 -5"));
//    }

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


//    calculator calcForLogging ;
//
//    @Mock
//    private Logger logger;
//    private CalcLoggingHandler calcHandler;
//
//    @Before
//    public void setUp(){
//        MockitoAnnotations.initMocks(this);
//        calcForLogging = new calculator(logger, calcHandler);
//    }
//
//    @Test
//    public void logSumWithMessage() {
//
//        calcForLogging.add("//$&&\n1&&5$13");
//
//        when(calcForLogging.getSum()).thenReturn(19);
//        when(calcForLogging.getLogData()).thenReturn("[*] SUM RESULT: "+calcForLogging.getSum());
//
//        final String messageToBeChecked = "[*] SUM RESULT: "+calcForLogging.getSum();
//
//        assertThat(calcForLogging.getLogData(), equalTo(messageToBeChecked));
//
//    }

    @Test
    public void logSumWithMessage() {

        calc.add("//$&&\n1&&5$13");
        final CalcLoggingHandler calcLoggingHandler = calc.getHandler();
        final Logger logger = calc.getCalcLogger();
        final String messageToBeChecked = "[*] SUM RESULT: "+calc.getSum();

        Logger.getLogger("main.java").addHandler(calcLoggingHandler);

        assertThat(calcLoggingHandler.getLogCapturedData(), equalTo(messageToBeChecked));

    }

    @Test
    public void logExceptionWithMessage() {
        calc.add("//$&&\n-1&&-5$-13");
        final CalcLoggingHandler calcLoggingHandler = calc.getHandler();
        final Logger logger = calc.getCalcLogger();
        final String messageToBeChecked = "[!] Negative Numbers Not Allowed Exception: "+calc.negativeNumbers;

        Logger.getLogger("main.java").addHandler(calcLoggingHandler);

        assertThat(calcLoggingHandler.getLogCapturedData(), equalTo(messageToBeChecked));

    }
}