package data_structures;

public class DataTypes {
    
    public String validateDataType(String input){
 
        String dataType = null;
 
        // checking for Integer
        if (input.matches("\\d+")) {
            dataType = "java.lang.Integer";
        }
 
        // checking for floating point numbers
        else if (input.matches("\\d*[.]\\d+")) {
            dataType = "java.lang.Double";
        }
 
        // checking for date format dd/mm/yyyy
        else if (input.matches(
                     "\\d{2}[/]\\d{2}[/]\\d{4}")) {
            dataType = "java.util.Date";
        }
 
        // checking for date format mm/dd/yyyy
        else if (input.matches(
                     "\\d{2}[/]\\d{2}[/]\\d{4}")) {
            dataType = "java.util.Date";
        }
 
        // checking for date format dd-mon-yy
        else if (input.matches(
                     "\\d{2}[-]\\w{3}[-]\\d{2}")) {
            dataType = "java.util.Date";
        }
 
        // checking for date format dd-mon-yyyy
        else if (input.matches(
                     "\\d{2}[-]\\w{3}[-]\\d{4}")) {
            dataType = "java.util.Date";
        }
 
        // checking for date format dd-month-yy
        else if (input.matches("\\d{2}[-]\\w+[-]\\d{2}")) {
            dataType = "java.util.Date";
        }
 
        // checking for date format dd-month-yyyy
        else if (input.matches("\\d{2}[-]\\w+[-]\\d{4}")) {
            dataType = "java.util.Date";
        }
 
        // checking for date format yyyy-mm-dd
        else if (input.matches(
                     "\\d{4}[-]\\d{2}[-]\\d{2}")) {
            dataType = "java.util.Date";
        }
 
        // checking for String
        else {
            dataType = "java.lang.String";
        }

        return dataType;
    }
    
}
