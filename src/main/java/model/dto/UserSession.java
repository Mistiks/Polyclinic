package model.dto;

import model.enums.Role;

public class UserSession {
    private String login;
    private Integer userId;
    private Integer doctorId;
    private Role role;

    public UserSession(String login, Integer userId, Integer doctorId, Role role) {
        this.login = login;
        this.userId = userId;
        this.doctorId = doctorId;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
