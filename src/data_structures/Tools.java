package data_structures;

import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Tools {
    
    
    protected Scanner sc = new Scanner(System.in);;
    protected int[] arr;
    protected String[] elements;
    
    public Tools(){
    }
    
    public Tools(int intArraySize, int stringArraySize){
        sc = new Scanner(System.in);
        arr = new int[intArraySize];
        elements = new String[stringArraySize];
    }
    
    public String measureTime(Instant start, Instant end){
        Duration interval = Duration.between(start, end);
        String response = "\nExecution time in seconds: " 
                + interval.getSeconds() + "\n";
        return response;
    }

    /* 
    * Casts from string to int, returns 
    * len, stands for array lenght
    */
    
    public int[] stringToIntArray(String[] elements, int len){ 

        for(int i = 0; i < 15; i++){
            if(elements[i] == null)
                break;
            else{
                arr[i] = Integer.valueOf(elements[i]);
            }
        }
        return arr;
    }
    
    /*
    * Fills a String array and calls validation input to know if it's a number.
    */
    public String[] fillStringArray(){
        //String[] elements;
        String element = "";
        int i = 0;
        
        do{
            System.out.print("\nPlease enter 15 numbers #" + i + ": ");
            if(element.equals("*") || i == 15)
                break;
            element = sc.nextLine();
            if(validateData(element) == true){
                elements[i] = element;
                i++;
            }else{ System.err.println("Error found, invalid input\n"); }
              
        }while(!element.equals("*"));
        
        return elements;
    }
    
    public char[] stringToChar(String s){ //Casts string to char to evaluate
        char[] c = new char[s.length()];
        for (int i = 0; i < s.length(); i++) { 
            c[i] = s.charAt(i); 
        }
        return c;
    }
    
    public boolean validateData(String s){ //Looks for special characters
        stringToChar(s);
        boolean result = false;
        int a = 0;
        char[] c = new char[s.length()];
        
        for(int i = 0; i < s.length(); i++){
            a = Character.getNumericValue(c[i]);
            if(a <= 9 && a >= 0){
                result = true;
            }
            else{
                result = false;
                break;
            }
        }
        return result;
    }
    
    public boolean isDigit(String input) {
        if (input == null || input.length() < 0)
            return false;
        
        input = input.trim();
        if ("".equals(input))
            return false;

        if (input.startsWith("-")) {
            return input.substring(1).matches("[0-9]*");
        } else {
            return input.matches("[0-9]*");
        }
    }
    
    public static int sum( int first, int last ){
        if ( first==last )
          return last;
        else
          return first + sum ( first+1, last );
    }
    

}
