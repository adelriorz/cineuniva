package tools;

/**
 * Ejercicio (2, 3 y 4)
 * Integra los ejercicios en un solo programa con un menú
 */

import java.util.Scanner;

class EjercicosRecursividad{

    public static double factorial(double n){ //Returns factorial of n number
        if(n<=1)
            return 1;
        else{
            System.out.println(n*n);
            return n * factorial(n-1);
        }
    }

    public static double fibonacci(Double x){ //Resturns fibonacci sum number
        if(x == 0){
            return 0;
        }else if(x == 1){
            return 1;
        }else{
            return fibonacci(x-1)+fibonacci(x-2);
        }
    }

    public static boolean palindrome(String word){
        String input = word;
		if(input.length() <= 2){
		    System.out.print("Congrats! " + input + " is a palindrome");
			return true;
		}
		else if(input.charAt(0) == input.charAt(input.length() -1)){
			palindrome(input.substring(1, input.length() -2));
			System.out.print("Congrats! " + input + " is a palindrome");
			return true;	
		}
		else {
			System.out.print("Sorry, " + input + " is not a palindrome");
			return false;
		}
    }

    /**
 * 1. Check if string is larger than 1 to discard if it's a char.
 * if it's then return ascii value.
 * 
 *  1.1 If char != {a...z} display error and asks for another value.
 *  1.2 Else parse char to int.
 * 
 * 2. Else, parse string to int.
 *  2.1 If 0 < int > 150, display error and asks for another value.
 * 
 * 3. Perform factorial of value displaying in terminal.
 */
    public static double parseInput(){
        double inputParsed = 0.0;
        String input = "";
        Scanner sc = new Scanner(System.in);
        
        do{
            System.out.print("\nPlease enter a number higher than 0 & lower than 150 " +
                    " or a letter of the abecedary: ");
            input = sc.nextLine();
            if(input.length() == 1){//It's a2 char, let's cast string to char and then to int
            System.out.println("You entered a char");
                char charInput = input.charAt(0);
                System.out.println("char: " + charInput);
                if(charInput>9) break;
                else{
                    inputParsed = (double) Character.digit(charInput, 10);
                    System.out.println("inputParsed: " + inputParsed);
                    //validates capital letters and lower case
                    if(inputParsed >= 65 || inputParsed <= 90 || inputParsed >= 97 || inputParsed <= 122)
                        return inputParsed;
                    else break;
                }
                
            } else {//it's an int. 
                System.out.println("You entered an int");
                inputParsed = Integer.parseInt(input); 
                if(inputParsed >= 151) {
                    System.out.println("\nNumber does not apply, please try again\n");
                } else return inputParsed;
            }
        }while(inputParsed <= 0 || inputParsed >= 151);
        sc.close();
        return inputParsed;
    }

    public static void menu(){
        int option = 0;
        String word = "";
        Scanner sc = new Scanner(System.in);
        
        do{
            System.out.println("\nWelcome!\nPlease, select an option:");
            System.out.print("\n1. Factorial.\n2. Fibonacci.\n3. Palindrome.\n"
            + "4.Exit\n\tOption: ");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println(factorial(parseInput()));
                    break;
                case 2:
                    System.out.println(fibonacci(parseInput()));
                    break;
                case 3:
                    System.out.print("\nPlease enter a word: ");
                    sc.nextLine();
                    word = sc.nextLine();
                    palindrome(word);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("\nInvalid key, try again\n");
                    break;
            }

        }while(option != 4);
        sc.close();
    }

    public static void main(String[] args) {
        EjercicosRecursividad.menu();        
    }
}