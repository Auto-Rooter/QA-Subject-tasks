package main.java.exercise1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class main {

    public static void main(String[] args){
        calculator c = new calculator(Logger.getLogger("main.java"), new CalcLoggingHandler());
        c.add("//$&&\n1&&5$13");
        System.out.println(c.getSum());
    }

}
