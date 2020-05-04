package main.java.exercise3;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class PassWordMain {

    public static void main(String args[])  {

        // -------------------   [1] Run With Single Thread  -------------------

                PasswordVerifier passwordVerifier = new PasswordVerifier();

                System.out.println("-------------------------- [*] Run With Single Thread ----------------------------");
                passwordVerifier.Verify("PASSWORD_");
                System.out.println(passwordVerifier.isPasswordValid());





        // -------------------   [2] Run With Multithreading  -------------------

                int threadsNumber = 2;
                ExecutorService pool = Executors.newFixedThreadPool(threadsNumber);

                Future<String> passwordVerifierWithMultithreading = pool.submit(new PasswordVerifierWithMultithreading("PassWord1122"));

                System.out.println("-------------------------- [*] Run With Multithreading -----------------------------");
                try{
                    System.out.println(passwordVerifierWithMultithreading.get());
                }catch (ExecutionException | InterruptedException ex){
                    ex.printStackTrace();
                }
                pool.shutdown();
    }
}
