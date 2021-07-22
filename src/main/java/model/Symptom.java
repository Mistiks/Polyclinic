package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "symptoms", schema = "polyclinic")
public class Symptom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "symptom_id")
    private Integer id;

    @Column(name = "symptom_name")
    private String symptomName;

    @Column(name = "symptom_code")
    private String symptomData;

    @ManyToMany(mappedBy = "symptoms")
    Set<Disease> diseases;

    public Symptom() {}

    public Symptom(String symptomName, String symptomData) {
        this.symptomName = symptomName;
        this.symptomData = symptomData;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSymptomName() {
        return symptomName;
    }

    public void setSymptomName(String symptomName) {
        this.symptomName = symptomName;
    }

    public String getSymptomData() {
        return symptomData;
    }

    public void setSymptomData(String symptomData) {
        this.symptomData = symptomData;
    }
}
