package Classes;

import Classes.Location;

public class State extends Location{
    
    protected Municipality mun;
    
    public State(int id, String name, boolean status){
        super();
        this.id = id;
        this.name = name;
        this.status = status;
    }
    
    public State(int id, String name, boolean status, Municipality mun){
        this.id = id;
        this.name = name;
        this.status = status;
        mun = new Municipality();
    }
    
    public State(){}

    public Municipality getMun() {
        return mun;
    }

    public void setMun(Municipality mun) {
        this.mun = mun;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
