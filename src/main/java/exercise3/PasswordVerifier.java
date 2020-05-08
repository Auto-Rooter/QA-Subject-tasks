package main.java.exercise3;

import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class PasswordVerifier {

    private Boolean largerThanEightFlag = false;
    private Boolean notNullFlag = false;
    private Boolean oneUpperLetterFlag = false;
    private Boolean oneLowerLetterFlag = false;
    private Boolean oneNumberFlag = false;
    private Hashtable<String, Boolean> cases = new Hashtable<String, Boolean>();
    private Hashtable<String, String> exceptionMessages = new Hashtable<String, String>();
    private String exceptionMessage = "";
    private Logger logger = Logger.getLogger("main.java");
    private PasswordLoggingHandler passwordLoggingHandler = new PasswordLoggingHandler();
    private Boolean exceptionTriggerFlag = false;

    public void Verify(String passWord){
        Logger.getLogger("main.java").addHandler(passwordLoggingHandler);

        if(notNullValidator(passWord)){
            notNullFlag = true;

            if(largerThanEightValidator(passWord)){
                largerThanEightFlag = true;
            }

            char letter ;

            for(int i=0; i<passWord.length(); i++){
                letter = passWord.charAt(i);

                if(oneNumberValidator(letter)){
                    oneNumberFlag = true;
                }

                if(oneUpperLetterValidator(letter)){
                    oneUpperLetterFlag = true;
                }

                if(oneLowerLetterValidator(letter)){
                    oneLowerLetterFlag = true;
                }

            }
        }
    }

    public String isPasswordValid(){
        Long numberOfValidCases = null;

        exceptionMessages.put("largerThanEightFlag", "[!] Exception: password should be larger than 8 chars");
        exceptionMessages.put("notNullFlag", "[!] Exception: password should not be null");
        exceptionMessages.put("oneUpperLetterFlag", "[!] Exception: password should have one uppercase letter at least");
        exceptionMessages.put("oneLowerLetterFlag", "[!] Exception: password should have one lowercase letter at least");
        exceptionMessages.put("oneNumberFlag", "[!] Exception: password should have one number at least");

         try {
            if (largerThanEightFlag) {
                 cases.put("largerThanEightFlag",true);
             } else {
                 cases.put("largerThanEightFlag",false);
             }

            if (notNullFlag) {
                cases.put("notNullFlag",true);
            } else {
                cases.put("notNullFlag",false);
            }

            if (oneUpperLetterFlag) {
                cases.put("oneUpperLetterFlag",true);
            } else {
                cases.put("oneUpperLetterFlag",false);
            }

            if (oneLowerLetterFlag) {
                cases.put("oneLowerLetterFlag",true);
            } else {
                cases.put("oneLowerLetterFlag",false);
            }

            if (oneNumberFlag) {
                cases.put("oneNumberFlag",true);
            } else {
                cases.put("oneNumberFlag",false);
            }

            cases.entrySet().stream()
                    .filter(element2 -> element2.getValue() == false)
                    .forEach(eee -> exceptionTriggerFlag =true);

            if(exceptionTriggerFlag){
                throw new IllegalArgumentException();
            }

        }finally {
             numberOfValidCases = cases.entrySet().stream()
                     .filter( element -> element.getValue() == true)
                     .count();

             exceptionMessage = cases.entrySet().stream()
                     .filter(element1 -> element1.getValue() == false)
                     .map( e1 -> exceptionMessages.get(e1.getKey()) )
                     .collect( Collectors.joining( ", \n" ) );

             if(!exceptionMessage.isEmpty()){
                 logger.log(Level.SEVERE,"\n"+ exceptionMessage);
             }

             return (numberOfValidCases >= 3 &&  cases.get("oneLowerLetterFlag") == true) ? "Password is OK" : "Password is never OK";
         }
    }

    public PasswordLoggingHandler getPasswordLoggingHandler(){
        return this.passwordLoggingHandler;
    }

    public Logger getLogger(){
        return this.logger;
    }


    public Boolean largerThanEightValidator(String password){
        return (password.length()>8) ? true: false;
    }

    public Boolean notNullValidator(String password){
        return password.isEmpty() ? false : true;
    }

    public Boolean oneNumberValidator(char letter){
        return Character.isDigit(letter) ? true : false;
    }

    public Boolean oneUpperLetterValidator(char letter){
       return Character.isUpperCase(letter) ? true : false;
    }

    public Boolean oneLowerLetterValidator(char letter){
        return Character.isLowerCase(letter) ? true : false;
    }

}
