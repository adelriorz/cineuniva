package Classes;

public class User {

    protected int id;
    protected String name;
    protected String password;
    protected boolean userType;
    protected boolean status;
    
    public User(){}
    
    public User(int id, String name, String password, boolean userType,
            boolean status){
        this.id = id;
        this.name = name;
        this.password = password;
        this.userType = userType;
        this.status = status;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isUserType() {
        return userType;
    }

    public void setUserType(boolean userType) {
        this.userType = userType;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
