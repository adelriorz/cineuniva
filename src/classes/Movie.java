package Classes;

public class Movie {
    
    protected int id;
    protected String name;
    protected String director;
    protected String producer;
    protected String classification;
    protected String duration;
    protected String genre;
    protected boolean status;
    
    public Movie(int id, String name, String director, String producer,
            String classification, String duration, String genre, boolean status)
    {
        this.id = id;
        this.name = name;
        this.director = director;
        this.producer = producer;
        this.classification = classification;
        this.duration = duration;
        this.genre = genre;
        this.status = status;
    }
    
    public Movie(){}

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

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getClasification() {
        return classification;
    }

    public void setClasification(String classification) {
        this.classification = classification;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    //Empty to define if they will stay
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public void searchMovie(){
        
    }
    
    public void createMovie(){
        
    }
    
    public void deleteMovie(){
        
    }
    
    public void modifyMovie(){
        
    }
    
    
}
