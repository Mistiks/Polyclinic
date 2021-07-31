package model.dto;

public class LoginData {
    private String username;
    private String password;

    public LoginData(String login, String password) {
        this.username = login;
        this.password = password;
    }

    public LoginData() {}

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
}
