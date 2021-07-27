package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "addresses", schema = "polyclinic")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "house")
    private String house;

    @Column(name = "flat")
    private String flat;

    @Column(name = "residence_country")
    private String residenceCountry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id", nullable=false, insertable = false, updatable = false)
    private User user;

    public Address() {}

    public Address(int id, String city, String street, String house, String flat, String country) {
        this.userId = id;
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
        this.residenceCountry = country;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getResidenceCountry() {
        return residenceCountry;
    }

    public void setResidenceCountry(String residenceCountry) {
        this.residenceCountry = residenceCountry;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
