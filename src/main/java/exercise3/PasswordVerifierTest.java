package main.java.exercise3;


import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.logging.Logger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;

public class PasswordVerifierTest {
    PasswordVerifier passwordVerifier = new PasswordVerifier();

    @Test
    public void passwordLessThanEightThrowException(){
        passwordVerifier.Verify("HADI");
        passwordVerifier.isPasswordValid();
        final PasswordLoggingHandler passwordLoggingHandler = passwordVerifier.getPasswordLoggingHandler();
        final Logger logger = passwordVerifier.getLogger();
        final String messageToBeChecked = "[!] Exception: password should be larger than 8 chars";

        Logger.getLogger("main.java").addHandler(passwordLoggingHandler);
        assertThat(passwordLoggingHandler.getLogCapturedData(), containsString(messageToBeChecked));
    }

    @Test
    public void passwordEqualNullThrowException(){
        passwordVerifier.Verify("");
        passwordVerifier.isPasswordValid();
        final PasswordLoggingHandler passwordLoggingHandler = passwordVerifier.getPasswordLoggingHandler();
        final Logger logger = passwordVerifier.getLogger();
        final String messageToBeChecked = "[!] Exception: password should not be null";

        Logger.getLogger("main.java").addHandler(passwordLoggingHandler);
        assertThat(passwordLoggingHandler.getLogCapturedData(), containsString(messageToBeChecked));
    }

    @Test
    public void passwordWithNoUpperCaseCharsThrowException(){
        passwordVerifier.Verify("aaaaaaa");
        passwordVerifier.isPasswordValid();
        final PasswordLoggingHandler passwordLoggingHandler = passwordVerifier.getPasswordLoggingHandler();
        final Logger logger = passwordVerifier.getLogger();
        final String messageToBeChecked = "[!] Exception: password should have one uppercase letter at least";

        Logger.getLogger("main.java").addHandler(passwordLoggingHandler);
        assertThat(passwordLoggingHandler.getLogCapturedData(), containsString(messageToBeChecked));
    }


    @Test
    public void passwordWithNoLowerCaseCharsThrowException(){
        passwordVerifier.Verify("AAAAAAAA");
        passwordVerifier.isPasswordValid();
        final PasswordLoggingHandler passwordLoggingHandler = passwordVerifier.getPasswordLoggingHandler();
        final Logger logger = passwordVerifier.getLogger();
        final String messageToBeChecked = "[!] Exception: password should have one lowercase letter at least";

        Logger.getLogger("main.java").addHandler(passwordLoggingHandler);
        assertThat(passwordLoggingHandler.getLogCapturedData(), containsString(messageToBeChecked));
    }


    @Test
    public void passwordWithNoNumbersThrowException(){
        passwordVerifier.Verify("aaaaAAAAD");
        passwordVerifier.isPasswordValid();
        final PasswordLoggingHandler passwordLoggingHandler = passwordVerifier.getPasswordLoggingHandler();
        final Logger logger = passwordVerifier.getLogger();
        final String messageToBeChecked = "[!] Exception: password should have one number at least";

        Logger.getLogger("main.java").addHandler(passwordLoggingHandler);
        assertThat(passwordLoggingHandler.getLogCapturedData(), containsString(messageToBeChecked));
    }

    @Test
    public void threeCasesValidReturnOkPassword(){
        passwordVerifier.Verify("aaaaAAAAD");
        assertEquals("Password is OK", passwordVerifier.isPasswordValid());
    }

    @Test
    public void lessThan8CharsAndNoLowercaseReturnNeverOk(){
        passwordVerifier.Verify("AAAA12");
        assertEquals("Password is never OK", passwordVerifier.isPasswordValid());
    }



}
