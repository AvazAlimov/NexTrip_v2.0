package Classes;

@SuppressWarnings("unused")
public class Guest extends User {
    private String username;

    Guest(){

    }

    public Guest(String login, String password, String username) {
        super(login, password);
        this.username = username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
