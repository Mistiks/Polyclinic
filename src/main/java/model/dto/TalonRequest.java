package model.dto;

public class TalonRequest {
    private String specialization;
    private String date;
    private String time;
    private Integer doctorId;

    public TalonRequest(String specialization, String date, String time, Integer doctorId) {
        this.specialization = specialization;
        this.date = date;
        this.time = time;
        this.doctorId = doctorId;
    }

    public TalonRequest(){}

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
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

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }
}
