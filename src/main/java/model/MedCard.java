package model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "med_card_data", schema = "polyclinic")
public class MedCard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "card_id")
    private Integer cardId;

    @Column(name = "doctor_id")
    private Integer doctorId;

    @Column(name = "disease_name")
    private String diseaseName;

    @Column(name = "diagnose_date")
    private LocalDate diagnoseDate;

    @Column(name = "discharge_date")
    private LocalDate dischargeDate;

    @ManyToOne
    @JoinColumn(name="id", nullable=false, insertable = false, updatable = false)
    private User user;

    public MedCard() {}

    public MedCard(Integer cardId, Integer doctorId, String diseaseName, LocalDate diagnoseDate, LocalDate dischargeDate) {
        this.cardId = cardId;
        this.diseaseName = diseaseName;
        this.diagnoseDate = diagnoseDate;
        this.dischargeDate = dischargeDate;
        this.doctorId = doctorId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
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

    public LocalDate getDiagnoseDate() {
        return diagnoseDate;
    }

    public void setDiagnoseDate(LocalDate diagnoseDate) {
        this.diagnoseDate = diagnoseDate;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }
}
