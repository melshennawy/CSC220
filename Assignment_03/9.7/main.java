/*
Author:       Erik Youngren <eyoungre@callutheran.edu>
Date Created: 2010-00-00

Purpose
=======
To solve Programming Exercise 9.7

    Phone Keypads
    -------------
    The international standard letter/number mapping found on the
    telephone is shown below:
        
    ==== ==== ====
    1    2    3   
         ABC  DEF 
    4    5    6   
    GHI  JKL  MNO 
    7    8    9   
    PQRS TUV  WXYZ
         0        
    ==== ==== ====
    
    Write a method that returns a number, given an uppercase letter, as
    follows::
        
        public static int getNumber(char uppercaseLetter)
    
    Write a test program that prompts the user to enter a phone number
    as a string. The input number may contain letters. The program
    translates a letter (upper- or lowercase) to a digit and leaves all
    other characters intact. Here is a sample run of the program::
        
        Enter a string: 1-800-Flowers
        1-800-3569377
    
    ::
        
        Enter a string: 1800flowers
        18003569377

Document History
================
============= =========================================================
Date Modified Reason
============= =========================================================
2010-00-00    Document Created
============= =========================================================

*/

import java.util.Scanner;

/**
 * Driver class
 */
class main {
    /** Lookup string for phone alphanumerics to numeric.
     * 
     * The trailing space is important.
     * 
     */
    static final String lookup =
        "ABC2 DEF3 GHI4 JKL5 MNO6 PQRS7 TUV8 WXYZ9 ";
    
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter a string: ");
        String telephone = input.nextLine();
        String telephone2 = "";
        
        for(int i = 0; i < telephone.length(); i++) {
            int digit = main.getNumber(telephone.charAt(i));
            
            // Valid responses are 2 through 9. Any other value is
            // wrong. In those cases, the original character is
            // preserved in the result.
            if(digit >= 2 && digit <= 9) {
                telephone2 += digit;
            } else {
                telephone2 += telephone.charAt(i);
            }
        }
        System.out.println(telephone2);
    }
    
    public static int getNumber(char uppercaseLetter) {
        // prep the character (just in case we get lower.)
        uppercaseLetter = Character.toUpperCase(uppercaseLetter);
        
        // We'll get this first since it's also a good way to determine
        // if a character is valid for this operation. A non -1
        // response means that the character is at least in the lookup
        // string.
        int index_of_char = main.lookup.indexOf(uppercaseLetter);
        
        // The second check filters out numeric digits, since the
        // first won't catch that. At first this was taking advantage
        // of the nature of ASCII (all alpha characters are
        // consecutive, so valid characters must fall in that range,)
        // but the user may not be using ASCII (or Unicode) and there
        // is at least one character set that does NOT have consecutive
        // alpha characters, so this will work on that kind of set.
        if (index_of_char != -1 && Character.isLetter(uppercaseLetter)) {
            // find the index of the space after the character group
            // the given character is in.
            int index_of_sep  = main.lookup.indexOf(" ", index_of_char);
            
            // extract the digit for that group and convert to integer
            int digit = Integer.parseInt(main.lookup.substring(
                index_of_sep - 1, index_of_sep));
            
            return digit;
        }
        
        return -1;
    }
}

