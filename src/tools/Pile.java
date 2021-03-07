package Tools;

public class Pile{
    
    protected int end = 0, start = 0, max = 0;
    protected int[] content;

    public Pile(int max){
        cancelPile();
        this.start = 0;
        this.max = max;
        this.content = new int[max];
    }
    
    public void setEnd(int end) {
        this.end = end;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getEnd() {
        return end;
    }

    public int getStart() {
        return start;
    }

    public int getMax() {
        return max;
    }
    
    public void setContent(int[] content) {
        this.content = content;
    }

    public int[] getContent() {
        return content;
    }
    
    //Method to print pile content
    public void selfPrintPile(){
        if(end < 0 ){
            System.out.print("\nEmpty pile.");
        } else
            for(int i=start; i <= end; i++)
                System.out.print(i + ", ");
    }
    
    public boolean isEmptyPile(){
        if(end < 0)
            return true;
        else return false;
    }
    
    public boolean isFullPile(){
        if(end == max) return true;
        else return false;
    }
    
    public void addValue(int value){
        if(isEmptyPile()){
            setEnd(0);
            content[end] = value;
        }
        if(!isEmptyPile() && !isFullPile()){
             end++;
            content[end] = value;
        } else System.out.print("\nPile is full.");
    }
    
    //Eliminates value from the pile
    public void deleteValue(){
        if(!isFullPile() && !isEmptyPile()){
            content[end] = 0;
            end --;
        } 
        if(isFullPile()){
            System.out.print("\nPile is full.");
        }
        if(isEmptyPile()){
            System.out.print("\nPile is empty.");
        }
    }
    
    public void cancelPile(){
        setEnd(-1);
    }
    
    public void activatePile(int end){
        setEnd(content.length);
    }
    

}
