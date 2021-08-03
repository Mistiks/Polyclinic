package view.api;

import model.dto.Appointment;

import javax.servlet.http.HttpServletRequest;

public interface ITalonService {
    void add(Integer userId, String visitTime, Integer doctorId);
    void addTalonsForToday(Integer doctorId);
    void create(Appointment appointment, HttpServletRequest request);
    void delete(Appointment appointment, HttpServletRequest request);
}
