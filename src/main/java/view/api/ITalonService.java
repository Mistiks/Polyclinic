package view.api;

import model.Talon;
import model.dto.Appointment;
import model.dto.Appointments;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ITalonService {
    void add(Integer userId, String visitTime, Integer doctorId);
    void addTalonsForToday(Integer doctorId);
    void create(Appointment appointment, HttpServletRequest request);
    void delete(Appointment appointment, HttpServletRequest request);
    void update(Appointment appointment, HttpServletRequest request);
    List<Appointments> getAllTalons(HttpServletRequest request);
}
