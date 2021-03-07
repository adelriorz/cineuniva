package Classes;

public class Schedule {
    
    //Also contains information regarding the available rooms
    
    protected int id;
    protected String startTimeMovie;
    protected String endTimeMovie;
    protected String dayNumber;
    protected String lockTime; //Duration of the movie + 30 mins room cleaning 
    protected boolean status;

    public Schedule(){}
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartDateMovie() {
        return startTimeMovie;
    }

    public void setStartDateMovie(String startTimeMovie) {
        this.startTimeMovie = startTimeMovie;
    }

    public String getEndDateMovie() {
        return endTimeMovie;
    }

    public void setEndDateMovie(String endTimeMovie) {
        this.endTimeMovie = endTimeMovie;
    }

    public String getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(String dayNumber) {
        this.dayNumber = dayNumber;
    }

    public String getLockTime() {
        return lockTime;
    }

    public void setLockTime(String lockTime) {
        this.lockTime = lockTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public void searchSchedule(){
        
    }
    
    public void createSchedule(){
        
    }
    
    public void deleteSchedule(){
        
    }
    
    public void modifySchedule(){
        
    }
    
}
