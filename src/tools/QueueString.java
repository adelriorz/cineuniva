package tools;

import java.util.Arrays;

/*
**Written by: Armando Del Río Ramírez
**Date: 01/05/ 2021 - 04/10/2021
**Description: Queue code with basic data structures for String
*/
public class QueueString{
    protected int end = 0, max = 0, start = 0;
    protected String[] content;

    public QueueString(int max){
        cancelQueue();
        this.start = 0;
        this.max = max;
        this.content = new String[max];
    }
    
    public int getEnd() {
        return end;
    }

    public void setEnd(int max) {
        this.end = max;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String[] getContent() {
        return content;
    }

    public void setContent(String[] content) {
        this.content = content;
    }
    
    public void addValue(String value){
        if(isEmptyQueue()){
            setEnd(end+1);
            content[end] = value;
        }
        if(!isEmptyQueue() && !isFullQueue()){
            content[end] = value;
            setEnd(end+1);
        }
    }
    
    //Method to move the queue forward
    public void pushValues(){
        for(int i = max; i <= 0; i--)
            content[end] = content[max-end];
    }
    
    public void deleteValue(){
        if(!isEmptyQueue()){
            pushValues();
            content[end] = null;
            --end;
        }
        if(isEmptyQueue()){
            System.out.print("\nQueue is empty.");
        }
    }
    
    public void selfPrintQueue(){
        if(end < 0 ){
            System.out.print("\nEmpty pile.");
        } else{
            System.out.println(Arrays.toString(content));
        }
    }
    
    public boolean isEmptyQueue(){
        if(end < 0)
            return true;
        else return false;
    }
    
    public boolean isFullQueue(){
        if(end == max) return true;
        else return false;
    }
    
    public void cancelQueue(){
        setEnd(-1);
    }
}