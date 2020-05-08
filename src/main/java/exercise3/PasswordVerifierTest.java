package main.java.exercise3;


import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.logging.Logger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void largerThanEightValdiatorShouldReturnTrue(){
        assertEquals(true, passwordVerifier.largerThanEightValidator("secretpassword"));
    }

    @Test
    public void notNullValidatorShouldReturnTrue(){
        assertEquals(true, passwordVerifier.notNullValidator("notNullPassword"));
    }

    @Test
    public void oneNumberValidatorShouldReturnTrue(){
        assertEquals(true, passwordVerifier.oneNumberValidator('3'));
    }

    @Test
    public void oneUpperLetterValidatorShouldReturnTrue(){
        assertEquals(true, passwordVerifier.oneUpperLetterValidator('H'));
    }

    @Test
    public void oneLowerLetterValidatorShouldReturnTrue(){
        assertEquals(true, passwordVerifier.oneLowerLetterValidator('h'));
    }

}
