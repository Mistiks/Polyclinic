package model.dto;

import java.time.LocalDate;

public class MedCardWithUsername {

    private String username;
    private String diseaseName;
    private LocalDate diagnoseDate;
    private LocalDate dischargeDate;
    private String notes;

    public MedCardWithUsername() {}

    public MedCardWithUsername(String username, String diseaseName, LocalDate diagnoseDate, LocalDate dischargeDate, String notes) {
        this.username = username;
        this.diseaseName = diseaseName;
        this.diagnoseDate = diagnoseDate;
        this.dischargeDate = dischargeDate;
        this.notes = notes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
