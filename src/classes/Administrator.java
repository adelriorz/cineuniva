package Classes;

public class Administrator extends User{

    private Movie movie;
    private Schedule schedule;
    private Billboard billboard;
    
    public Administrator(int id, String name, String password, boolean status,
        boolean userType){
        super();
        this.id = id;
        this.name = name;
        this.password = password;
        this.status = status;
        this.userType = userType;
    }
    
    public Administrator(){}
    //Movie Section
    public void createMovie(){}
    
    //Deactivates Movie
    public void deleteMovie(){
    
    }
    
    //Modifies Movie
    public void modifyMovie(){
    
    }
    
    //Shows grater or lower according to day and hour
    public void sortsBillboard(){
        
    }
    
    //Shows all movies in DB
    public void showBillboard(){
        
    }
    
    //You may check all info from one particular movie
    public void readMovie(){
    
    }
    
    //Schedule Section
    //Create new Schedule
    public void createSchedule(){
    
    }
    
    //Deactivates a Schedule
    public void deleteSchedule(){
    
    }
    
    /*
    * Este metodo muestra el menu de administrador
    */
    public void adminMenu(){
        short option = 0;
        
        do{
            switch(option){
                case 1: movie.createMovie();
                    break;
                case 2: schedule.createSchedule();
                    break;
                case 3: movie.deleteMovie();
                    break;
                case 4: schedule.deleteSchedule();
                    break;
                case 5: movie.modifyMovie();
                    break;
                case 6: schedule.modifySchedule();
                    break;
                case 7: billboard.showBillboard();
                    break;
                case 8: 
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.print("\n\nOpcion incorrecta, "
                            + "intente de nuevo.");
                break;
            }
        }while(option != 8);
        
    }
            
}
