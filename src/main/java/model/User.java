package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "User")
@Table(name = "users", schema = "polyclinic")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "login")
    private String login;

    @Column(name = "status")
    private String status;

    @Column(name = "phone_number")
    private String number;

    @Column(name = "user_role")
    private String role;

    @Column(name = "user_position")
    private String position;

    @Column(name = "doctor_id")
    private int doctorId;

    @Column(name = "past_position")
    private String pastPosition;

    @Column(name = "cabinet_num")
    private int cabinetNum;

    @Column(name = "department")
    private int department;

    @ManyToOne
    @JoinColumn(name="department", nullable=false, insertable = false, updatable = false)
    private Department departments;

    @Column(name = "picture")
    private String picture;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "login", referencedColumnName = "user_login", insertable = false, updatable = false)
    private Password password;

    @OneToMany(mappedBy="user")
    private Set<Address> addresses;

    @OneToMany(mappedBy="user")
    private Set<Passport> passports;

    @OneToMany(mappedBy="user")
    private Set<MedCard> medcards;


    public User() {}

    public User(String login) {
        this.login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getPastPosition() {
        return pastPosition;
    }

    public void setPastPosition(String pastPosition) {
        this.pastPosition = pastPosition;
    }

    public int getCabinetNum() {
        return cabinetNum;
    }

    public void setCabinetNum(int cabinetNum) {
        this.cabinetNum = cabinetNum;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
