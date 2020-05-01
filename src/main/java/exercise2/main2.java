package main.java.exercise2;

import java.util.logging.Logger;

public class main2 {

    public static void main(String[] args){
        calculator2 c = new calculator2(Logger.getLogger("main.java"), new CalcLoggingHandler());
        c.add("//$&&\n1&&5$13");
        System.out.println(c.getSum());
    }

}
