package model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "passport_data", schema = "polyclinic")
public class Passport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "passport_id")
    private String passportId;

    @Column(name = "passport_num")
    private String passportNum;

    @Column(name = "country_name")
    private String country;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "sex")
    private String sex;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Column(name = "expire_date")
    private LocalDate expireDate;

    @Column(name = "birth_place")
    private String birthCountry;

    @ManyToOne
    @JoinColumn(name="id", nullable=false, insertable = false, updatable = false)
    private User user;

    public Passport() {}

    public Passport(int userId, String passportId, String passportNum, String country, String nationality, LocalDate birthDate, String sex, LocalDate issueDate, LocalDate expireDate, String birth) {
        this.userId = userId;
        this.passportId = passportId;
        this.passportNum = passportNum;
        this.country = country;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.sex = sex;
        this.issueDate = issueDate;
        this.expireDate = expireDate;
        this.birthCountry = birth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public String getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public String getBirthCountry() {
        return birthCountry;
    }

    public void setBirthCountry(String birthCountry) {
        this.birthCountry = birthCountry;
    }
}
