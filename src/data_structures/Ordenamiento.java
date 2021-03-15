package data_structures;

import java.util.Scanner;
import java.util.Arrays;
import java.time.Duration;
import java.time.Instant;

class Ordenamiento{

    protected int[] intArr;
    protected String[] elements;
    char[] c;
    protected Scanner sc;
    
    public Ordenamiento(){
        intArr = new int[15];
        elements = new String[15];
    }

    public void bubbleSort(int[] arr){
        Instant start = Instant.now();
        int n = arr.length; 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (arr[j] > arr[j+1]){ 
                    int temp = arr[j]; 
                    arr[j] = arr[j+1]; 
                    arr[j+1] = temp; 
                }
        Instant end = Instant.now();
        System.out.println("Lower:" + Arrays.toString(arr));
        
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (arr[j] < arr[j+1]){
                    int temp = arr[j]; 
                    arr[j] = arr[j+1]; 
                    arr[j+1] = temp; 
                } 
        
        System.out.println("Greater:" + Arrays.toString(arr));
        System.out.println(measureTime(start, end));
    }

    public void enhancedBubbleSort(int[] arr){
        if(arr==null || arr.length==0)
            return;

        int size = arr.length;
        for (int i = 0; i < size-1 ; i++)
            for (int j = 0; j < size-i-1 ; j++)
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
                
        System.out.println("Lower:" + Arrays.toString(arr));
        
        Instant start = Instant.now();
        for (int i = 0; i < size-1 ; i++)
            for (int j = 0; j < size-i-1 ; j++)
                if(arr[j] < arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
                
        Instant end = Instant.now();
        System.out.println("Greater:" + Arrays.toString(arr));
        System.out.print(measureTime(start, end));
    }

    public void selectionSort(int[] arr){
        Instant start = Instant.now();
        int n = arr.length; 
  
        for (int i = 0; i < n-1; i++){ 
            int min_idx = i; 
            for (int j = i+1; j < n; j++) 
                if (arr[j] < arr[min_idx]) 
                    min_idx = j; 
  
            int temp = arr[min_idx]; 
            arr[min_idx] = arr[i]; 
            arr[i] = temp; 
        }
        Instant end = Instant.now();
        System.out.println("Lower:" + Arrays.toString(arr));
        
        for (int i = 0; i < n-1; i++){ 
            int min_idx = i; 
            for (int j = i+1; j < n; j++) 
                if (arr[j] > arr[min_idx]) 
                    min_idx = j; 
  
            int temp = arr[min_idx]; 
            arr[min_idx] = arr[i]; 
            arr[i] = temp; 
        }
        System.out.println("Greater:" + Arrays.toString(arr));
        System.out.println(measureTime(start, end));
    }
    
    public void quickSort(int[] arr){
        
        Instant start = Instant.now();
        sortQuick(arr, 0, arr.length-1);        
        Instant end = Instant.now();
        
        System.out.println("Lower:" + Arrays.toString(arr));
        System.out.print("Greater: [");
        for (int i = arr.length-1; i >= 0; i--) {  
            System.out.print(arr[i] + ", ");  
        }
        System.out.print("]");
        System.out.println(measureTime(start, end));
    }
    
    int partition(int arr[], int low, int high){ 
        int pivot = arr[high];  
        int i = (low-1);
        for (int j=low; j<high; j++){
            if (arr[j] < pivot){ 
                i++; 
                int temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
            } 
        }
        int temp = arr[i+1]; 
        arr[i+1] = arr[high]; 
        arr[high] = temp; 
        return i+1; 
    }
  
    void sortQuick(int arr[], int low, int high){
        if (low < high){ 
            int pi = partition(arr, low, high); 
            sortQuick(arr, low, pi-1); 
            sortQuick(arr, pi+1, high); 
        } 
    }
    
    public void mergeSort(int[] arr){
        
        Instant start = Instant.now();
        sortMerge(arr, 0, arr.length - 1);
        Instant end = Instant.now();
        
        System.out.println("Lower:" + Arrays.toString(arr));
        System.out.print("Greater: [");
        for (int i = arr.length-1; i >= 0; i--) {  
            System.out.print(arr[i] + ", ");  
        }
        System.out.print("]");
        System.out.println(measureTime(start, end));
    }
     
    void sortMerge(int arr[], int l, int r) 
    { 
        if (l < r) { 
            int m = (l + r) / 2; 
  
            sortMerge(arr, l, m); 
            sortMerge(arr, m + 1, r); 

            merge(arr, l, m, r); 
        } 
    } 
    
    void merge(int arr[], int l, int m, int r){
        
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        int L[] = new int[n1]; 
        int R[] = new int[n2]; 
  
        for (int i = 0; i < n1; ++i) 
            L[i] = arr[l + i]; 
        for (int j = 0; j < n2; ++j) 
            R[j] = arr[m + 1 + j]; 
    
        int i = 0, j = 0; 
  
        int k = l; 
        while (i < n1 && j < n2) { 
            if (L[i] <= R[j]) { 
                arr[k] = L[i]; 
                i++; 
            } 
            else { 
                arr[k] = R[j]; 
                j++; 
            } 
            k++; 
        } 
  
        while (i < n1) { 
            arr[k] = L[i]; 
            i++; 
            k++; 
        } 
  
        while (j < n2) { 
            arr[k] = R[j]; 
            j++; 
            k++; 
        } 
    }
    
    public void shellSort(int[] arr){
        
        Instant start = Instant.now();      
        int n = arr.length; 
  
        for (int gap = n/2; gap > 0; gap /= 2){ 
            for (int i = gap; i < n; i += 1){ 
                int temp = arr[i]; 
  
                int j; 
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) 
                    arr[j] = arr[j - gap];
                arr[j] = temp; 
            } 
        }
        Instant end = Instant.now();
        
        System.out.println("Lower:" + Arrays.toString(arr));
        System.out.print("Greater: [");
        for (int i = arr.length-1; i >= 0; i--) {  
            System.out.print(arr[i] + ", ");  
        }
        System.out.print("]");
        System.out.println(measureTime(start, end));
    }
    
    public String measureTime(Instant start, Instant end){
        Duration interval = Duration.between(start, end);
        String response = "\nExecution time in seconds: " 
                + interval.getSeconds() + "\n";
        return response;
    }

    public int[] stringToIntArray(String[] elements){//Casts from string to int
        
        for(int i = 0; i < 15; i++){
            if(elements[i] == null)
                break;
            else{
                intArr[i] = Integer.valueOf(elements[i]);
            }
        }
        return intArr;
    }
    
    public String[] fillStringArray(){ //Fills array
        
        String element = "";
        sc = new Scanner(System.in);
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
        c = new char[s.length()];
        for (int i = 0; i < s.length(); i++) { 
            c[i] = s.charAt(i); 
        }
        return c;
    }
    
    public boolean validateData(String s){ //Looks for special characters
        stringToChar(s);
        boolean result = false;
        int a = 0;
        
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
    
    public void menu(){
        int option = 0;
        intArr = stringToIntArray(fillStringArray());
        
        do{
            
            System.out.print("**Please select an option**\n"
            + "1. Bubble Sort Ascendant.\n2. Enhanced Bubble Sort.\n"
            + "3. Selection Sort.\n4. Quick Sort.\n5. Merge Sort."
            + "\n6. Shell Sort.\n7. Exit\n\tOption: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    bubbleSort(intArr);
                    break;
                case 2:
                    enhancedBubbleSort(intArr);
                    break;
                case 3:
                    selectionSort(intArr);
                    break;
                case 4:
                    quickSort(intArr);
                    break;
                case 5:
                    mergeSort(intArr);
                    break;
                case 6:
                    shellSort(intArr);
                    break;
                case 7:
                    break;
                default:
                    System.err.println("Invalid key.\n");
                    break;
            }
            
        }while(option != 7);
        sc.close();
        
    }
    
}