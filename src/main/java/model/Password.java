package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "login_data", schema = "polyclinic")
public class Password implements Serializable {

    @Id
    @Column(name = "user_login")
    private String login;

    @Column(name = "hash")
    private String hash;

    @Column(name = "salt")
    private String salt;

    @OneToOne(mappedBy = "password")
    private User user;

    public Password() {}

    public Password(String login, String hash, String salt) {
        this.login = login;
        this.hash = hash;
        this.salt = salt;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
