package model.dto;

public class TalonResponse {
    private Integer doctorId;
    private String fio;

    public TalonResponse(){}

    public TalonResponse(Integer doctorId, String fio) {
        this.doctorId = doctorId;
        this.fio = fio;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
}
