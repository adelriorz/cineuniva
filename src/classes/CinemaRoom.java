package Classes;

public class CinemaRoom {
    
    protected int id;
    protected int roomsNumber; //Available rooms in location
    
    public CinemaRoom(){
        
    }
    
    //public CinemaRoom(){}
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomsNumber() {
        return roomsNumber;
    }

    public void setRoomsNumber(int roomsNumber) {
        this.roomsNumber = roomsNumber;
    }
    
}
