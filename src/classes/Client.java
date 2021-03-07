package Classes;

public class Client extends User{
    
    private Movie movie;
    private Billboard billboard;
    
    public Client(int id, String name, String password, boolean status,
            boolean userType){
        super();
        this.id = id;
        this.name = name;
        this.password = password;
        this.status = status;
        this.userType = userType;
    }

    public Client(){}
    
    public String searchMovieByName(){
        return "";
    }
    
    public void searchMovieByClassification(){
        
    }
    
    //Searches movie by genre
    public void searchMovieByGenre(){
        
    }
    
    //Sorts Billboard from greater to lower
    public void sortBillboardGreater(){
        
    }
    
    //Sorts Billboard from lower to greater
    public void sortBillboardLower(){
        
    }
    
    //Shows all movies in DB
    public void showBillboard(){
        
    }
    
    public void clientMenu(){
        short option = 0;
        do{
            
            switch(option){
                case 1: movie.searchMovie();//Search by name
                    break;
                case 2: movie.searchMovie();//Search by classification
                    break;
                case 3: movie.searchMovie();//Search by genre
                    break;
                case 4: billboard.sortBillboard();
                    break;
                case 5: movie.searchMovie();
                    break;
                case 6: billboard.showBillboard();
                    break;
                case 7: 
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.print("\n\nOpcion incorrecta, "
                            + "intente de nuevo.");
                break;
            }
        }while(option != 7);
    }
    
}
