package tools;
//Changed the usage, instead

public class QueueString{
    protected int end = 0, start = 0, max = 0;
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

    public void setEnd(int end) {
        this.end = end;
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
            setEnd(end);
            content[end] = value;
        }
        if(!isEmptyQueue() && !isFullQueue()){
            end++;
            content[end++] = value;
        } else System.out.print("\nQueue is full.");
    }
    
    //Method to move the queue forward
    public void pushValues(){
        for(int i = 0; i <= end; i++)
            content[i] = content[i+1];
    }
    
    public void deleteValue(){
        if(!isEmptyQueue() && !isFullQueue()){
            pushValues();
            content[end] = "";
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
            for(int i=start; i <= end; i++){
                System.out.print(content[i] + ", ");
            }
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