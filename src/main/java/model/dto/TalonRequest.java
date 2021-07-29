package model.dto;

public class TalonRequest {
    private String specialization;
    private String fio;
    private String date;
    private String time;

    public TalonRequest(String specialization, String fio, String date, String time) {
        this.specialization = specialization;
        this.fio = fio;
        this.date = date;
        this.time = time;
    }

    public TalonRequest(){}

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
