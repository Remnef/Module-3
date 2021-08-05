package back_end.model;

public class Account {
    private int id;
    private String username;
    private String password;
    private int isUser = 0;
    private int isAdmin = 0;
    private String name;
    private String email;
    private int status = 1;

    public Account(){

    }

    public Account(int id, String username, String password, int isUser, int isAdmin, String name, String email, int status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isUser = isUser;
        this.isAdmin = isAdmin;
        this.name = name;
        this.email = email;
        this.status = status;
    }

    public Account(int id, String username, String passwork, int isUser, int isAdmin, String name, String email) {
        this.id = id;
        this.username = username;
        this.password = passwork;
        this.isUser = isUser;
        this.isAdmin = isAdmin;
        this.name = name;
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsUser() {
        return isUser;
    }

    public void setIsUser(int isUser) {
        this.isUser = isUser;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", passwork='" + password + '\'' +
                ", isUser=" + isUser +
                ", isAdmin=" + isAdmin +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
