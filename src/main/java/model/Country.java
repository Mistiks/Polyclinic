package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "countries", schema = "polyclinic")
public class Country implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_code")
    private Integer code;

    @Column(name = "country_name")
    private String name;

    @Column(name = "country_name_full")
    private String nameFull;

    @Column(name = "name_2_chars")
    private String nameTwoChars;

    @Column(name = "name_3_chars")
    private String nameThreeChars;

    public Country() {}

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameFull() {
        return nameFull;
    }

    public void setNameFull(String nameFull) {
        this.nameFull = nameFull;
    }

    public String getNameTwoChars() {
        return nameTwoChars;
    }

    public void setNameTwoChars(String nameTwoChars) {
        this.nameTwoChars = nameTwoChars;
    }

    public String getNameThreeChars() {
        return nameThreeChars;
    }

    public void setNameThreeChars(String nameThreeChars) {
        this.nameThreeChars = nameThreeChars;
    }
}
