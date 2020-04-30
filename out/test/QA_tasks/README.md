# Quality Assurance Subject (Miskolc University)

## - Exercise 1: 

- Create a simple String calculator with a method signature:
- The method can take up to __two__ numbers, separated by __commas__, and will return their __sum__. 
- Allow the Add method to handle an __unknown__ amount of numbers.
- Allow the Add method to handle __new lines__ between numbers (instead of commas).
- Support different delimiters.
- Numbers bigger than `1000` should be ignored, so adding `2 + 1001 = 2`
- Delimiters can be of any length with the following format: `“//[delimiter]\n”` for example: `“//[***]\n1***2***3”` should return `6`
- Allow multiple delimiters like this:
     > “[delim1][delim2]\n” for example “[*][%]\n1*2%3” should return 6.

- make sure you can also handle multiple delimiters with length longer than one char.



## - Exercise 2: 
 - Add Logging Abilities to your new String Calculator (to an ILogger.Write()) interface (you will need a mock).
 - Every time you call Add(), the sum result will be logged to the logger.
 - When calling Add() and the logger throws an exception, the string calculator should notify an IWebservice of some<br>
  kind that logging has failed with the message from the logger’s exception (you will need a mock and a stub).

## - Exercise 3: 

## - Exercise 4: 

