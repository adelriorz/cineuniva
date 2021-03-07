package Classes;

public class Billboard {
    
    protected boolean status;
    protected State state;
    protected Municipality municipality;
    protected CinemaRoom cinemaroom;
    protected Movie movie;
    protected Schedule schedule;
    
    public Billboard(State state, Municipality municipality, Schedule schedule,
        Movie movie){
        this.state = new State();
        this.municipality = new Municipality();
        this.movie = new Movie();
        this.schedule = new Schedule();
        this.cinemaroom = new CinemaRoom();
        this.status = true;
    }
    
    public Billboard(){}
    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public void searchSchedule(){
        
    }
    
    public void createBillboard(){
        
    }
    
    public void deleteBillboard(){
        
    }
    
    public void modifyBillboard(){
        
    }
    
    public void showBillboard(){
        
    }
    
    public void sortBillboard(){
        
    }
    
}
