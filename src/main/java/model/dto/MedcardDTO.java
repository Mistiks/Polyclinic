package model.dto;

public class MedcardDTO {

    private Integer medcardId;
    private String username;
    private String diseaseName;
    private String diagnoseDate;
    private String dischargeDate;
    private String notes;

    public MedcardDTO() {}

    public MedcardDTO(Integer medcardId, String username, String diseaseName,
                      String diagnoseDate, String dischargeDate, String notes) {
        this.medcardId = medcardId;
        this.username = username;
        this.diseaseName = diseaseName;
        this.diagnoseDate = diagnoseDate;
        this.dischargeDate = dischargeDate;
        this.notes = notes;
    }

    public Integer getMedcardId() {
        return medcardId;
    }

    public void setMedcardId(Integer medcardId) {
        this.medcardId = medcardId;
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

    public String getDiagnoseDate() {
        return diagnoseDate;
    }

    public void setDiagnoseDate(String diagnoseDate) {
        this.diagnoseDate = diagnoseDate;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(String dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
