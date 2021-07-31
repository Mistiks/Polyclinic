package model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "talon", schema = "polyclinic")
public class Talon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "talon_id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "visit_time")
    private LocalDateTime visitTime;

    @Column(name = "doctor_id")
    private Integer doctorId;

    @ManyToOne
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    private User user;

    public Talon() {}

    public Talon(Integer userId, LocalDateTime visitTime, Integer doctorId) {
        this.userId = userId;
        this.visitTime = visitTime;
        this.doctorId = doctorId;
    }

    public Talon(Integer id, Integer userId, LocalDateTime visitTime, Integer doctorId) {
        this.id = id;
        this.userId = userId;
        this.visitTime = visitTime;
        this.doctorId = doctorId;
    }

    public Talon(LocalDateTime visitTime, Integer doctorId) {
        this.visitTime = visitTime;
        this.doctorId = doctorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalDateTime visitTime) {
        this.visitTime = visitTime;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }
}
