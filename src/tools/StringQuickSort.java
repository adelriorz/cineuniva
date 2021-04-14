package tools;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
**Written by: John Smith
**Date: 01/05/ 2021 - 04/10/2021
**Description: Adaptation for QuickSort Algorythm to String[]
*/

public class StringQuickSort {
    
    private String names[];
    private int length;

    //Driver method to run the class
    public static void main(String[] args) {
        StringQuickSort qS = new StringQuickSort();
        String words[] = {"zz", "aa", "cc", "hh", "bb", "ee", "ll"}; // the strings need to be sorted are put inside this array
        qS.sort(words);
        qS.sortZA(words);

        for (String i : words) {
            System.out.print(i);
            System.out.print(" ");
        }
    }
    //This method pases from List to ArrayString
    public static String[] parseListToArr(List<String> list){
        String[] array = list.stream().toArray(String[]::new);
        return array;
    }
    
    //Cheks if array is not null and sets first values
    public void sort(String array[]) {
        if (array == null || array.length == 0) {
            return;
        }
        this.names = array;
        this.length = array.length;
        quickSort(0, length - 1);
    }

    //Recursive algorythm
    public void quickSort(int lowerIndex, int higherIndex) {
        int i = lowerIndex;
        int j = higherIndex;
        String pivot = this.names[lowerIndex + (higherIndex - lowerIndex) / 2];

        while (i <= j) {
            while (this.names[i].compareToIgnoreCase(pivot) < 0) {
                i++;
            }

            while (this.names[j].compareToIgnoreCase(pivot) > 0) {
                j--;
            }

            if (i <= j) {
                exchangeNames(i, j);
                i++;
                j--;
            }
        }
        //call quickSort recursively
        if (lowerIndex < j) {
            quickSort(lowerIndex, j);
        }
        if (i < higherIndex) {
            quickSort(i, higherIndex);
        }
    }

    public void exchangeNames(int i, int j) {
        String temp = this.names[i];
        this.names[i] = this.names[j];
        this.names[j] = temp;
    }
    
    //Cheks if array is not null and sets first values
    public void sortZA(String array[]) {
        if (array == null || array.length == 0) {
            return;
        }
        this.names = array;
        this.length = array.length;
        quickSortZA(0, length - 1);
    }

    //Recursive algorythm quickSortZA
    public void quickSortZA(int lowerIndex, int higherIndex) {
        int i = lowerIndex;
        int j = higherIndex;
        String pivot = this.names[lowerIndex + (higherIndex - lowerIndex) / 2];

        while (i <= j) {
            while (this.names[i].compareToIgnoreCase(pivot) > 0) {
                i++;
            }

            while (this.names[j].compareToIgnoreCase(pivot) < 0) {
                j--;
            }

            if (i <= j) {
                exchangeNames(i, j);
                i++;
                j--;
            }
        }
        //call quickSortZA recursively
        if (lowerIndex < j) {
            quickSortZA(lowerIndex, j);
        }
        if (i < higherIndex) {
            quickSortZA(i, higherIndex);
        }
    }
    
}