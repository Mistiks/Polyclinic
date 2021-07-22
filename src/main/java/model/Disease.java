package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "disease_data", schema = "polyclinic")
public class Disease implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disease_id")
    private Integer id;

    @Column(name = "disease_name")
    private String diseaseName;

    @Column(name = "disease_code")
    private String diseaseCode;

    @ManyToMany
    @JoinTable(
            name = "disease_symptoms",
            joinColumns = @JoinColumn(name = "disease_id"),
            inverseJoinColumns = @JoinColumn(name = "symptom_id"))
    Set<Symptom> symptoms;

    public Disease() {}

    public Disease(String diseaseName, String diseaseCode) {
        this.diseaseName = diseaseName;
        this.diseaseCode = diseaseCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getDiseaseData() {
        return diseaseCode;
    }

    public void setDiseaseData(String diseaseCode) {
        this.diseaseCode = diseaseCode;
    }
}
