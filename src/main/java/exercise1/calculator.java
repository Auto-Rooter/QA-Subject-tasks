package main.java.exercise1;


import java.util.ArrayList;

public class calculator {

    private int total = 0;
    private String separators = "[;,\n]";
    private Boolean exceptionFlag = false;
    private String negativeNumbers = " ";
    private String bannedCharacters = "$+*^";
    private String delimiterPart = "";
    private ArrayList<String> multidigitDelimitersList = new ArrayList<>();
    private String multidigitDelimiters = "";

    public void add(String sentence) {
        if(sentence.isEmpty()){
            total = 0;
            return;
        }

        if(sentence.charAt(0) == '/'){
            delimiterPart = sentence.split("\n")[0];

            if(delimiterPart.substring(2).length() == 1){
                separators=delimiterPart.substring(2);
            }
            separators = "[;,\n"+separators+"]";
            if(delimiterPart.substring(2).length() > 1){
                String currentDelimiter = " ";
                for(String deliChar : delimiterPart.substring(2).split("")){

                    if(currentDelimiter.charAt(currentDelimiter.length()-1) == deliChar.charAt(0)){
                        if(bannedCharacters.contains(deliChar)){
                            currentDelimiter+="\\"+deliChar;
                        }else{
                            currentDelimiter+=deliChar;
                        }
                    }else{
                        multidigitDelimitersList.add(currentDelimiter);
                        if(bannedCharacters.contains(deliChar)){
                            currentDelimiter="\\"+deliChar;
                        }else{
                            currentDelimiter=deliChar;
                        }
                    }
                }
                multidigitDelimitersList.add(currentDelimiter);
                multidigitDelimitersList.remove(0);

                for(String delii : multidigitDelimitersList){
                    multidigitDelimiters=multidigitDelimiters+"|"+delii;
                }
                separators = separators+multidigitDelimiters;
            }
            sentence = sentence.split("\n")[1];
        }

        //List<String> cleanElements = Arrays.asList(sentence.trim().split("["+separators+"]|"+multidigitDelimiter)).stream().filter(s->!s.isEmpty()).collect(Collectors.toList());
        for(String num : sentence.trim().split(separators)){

            if(Integer.parseInt(num.trim())<0){
                exceptionFlag= true;
                negativeNumbers+=num+" ";
            }else if(Integer.parseInt(num)<=1000) {
                total += Integer.parseInt(num);
            }
        }

        if(exceptionFlag){
            throw new IllegalArgumentException("Negatives Not Allowed:"+negativeNumbers);
        }
    }

    public int getSum(){
        return total;
    }

}




