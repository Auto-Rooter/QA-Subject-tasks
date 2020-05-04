package main.java.exercise3;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import static org.junit.Assert.assertEquals;

public class PasswordVerifierWithMultithreadingTest {

    ExecutorService pool = Executors.newFixedThreadPool(2);

    @Rule
    public Timeout verificationTimeout = Timeout.seconds(1);

    @Test
    public void passwordVerificationTakeLessThanOneSecond() {
        Future<String> passwordVerifierWithMultithreading = pool.submit(new PasswordVerifierWithMultithreading("HADIfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffHADIfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffHADIfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"));
        try{
            passwordVerifierWithMultithreading.get();

            // You can verify it by un-commenting the line below, So the test will take more than 1 second and as expected the test will fail.

            //Thread.currentThread().sleep(5000);

        }catch (InterruptedException | ExecutionException ex){
            ex.printStackTrace();
        }
        pool.shutdown();

    }

    @Test
    public void threeCasesValidReturnOkPasswordTakeLessThanOneSecond(){

        Future<String> passwordVerifierWithMultithreading = pool.submit(new PasswordVerifierWithMultithreading("aaaaAAAAD"));

        try{
            assertEquals("Password is OK", passwordVerifierWithMultithreading.get());
        }catch (InterruptedException | ExecutionException ex){
            ex.printStackTrace();
        }
        pool.shutdown();
    }

    @Test
    public void lessThan8CharsAndNoLowercaseReturnNeverOkTakeLessThanOneSecond(){

        Future<String> passwordVerifierWithMultithreading = pool.submit(new PasswordVerifierWithMultithreading("AAAA12"));

        try{
            assertEquals("Password is never OK", passwordVerifierWithMultithreading.get());
        }catch (InterruptedException | ExecutionException ex){
            ex.printStackTrace();
        }
        pool.shutdown();
    }
}
