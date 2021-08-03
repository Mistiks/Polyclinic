package view;

import model.Talon;
import model.User;
import model.dto.Appointment;
import model.enums.TalonTime;
import storage.api.ITalonRepository;
import storage.api.IUserRepository;
import view.api.ITalonService;
import view.api.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TalonService implements ITalonService {
    private final IUserService userService;
    private final ITalonRepository repository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private final String CURRENT_USER = "currentUser";

    public TalonService(IUserService userService, ITalonRepository repository) {
        this.userService = userService;
        this.repository = repository;
    }

    @Override
    public void add(Integer userId, String visitTimeString, Integer doctorId) {
        LocalDateTime visitTime;

        try {
            visitTime = LocalDateTime.parse(visitTimeString, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Entered time is wrong!");
        }

        Talon checkingTalon = repository.isTalonBooked(doctorId, visitTime);

        if (checkingTalon == null) {
            Talon talon = new Talon(userId, visitTime, doctorId);
            repository.save(talon);
        } else {
            throw new IllegalArgumentException("Talon already booked!");
        }
    }

    @Override
    public void addTalonsForToday(Integer doctorId) {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime;
        LocalDateTime localDateTime;
        Talon talon;
        TalonTime[] timeValues = TalonTime.values();

        for (TalonTime timeValue : timeValues) {
            currentTime = LocalTime.parse(timeValue.getTime(), timeFormatter);
            localDateTime = LocalDateTime.of(currentDate, currentTime);
            talon = new Talon(localDateTime, doctorId);
            repository.save(talon);
        }
    }

    @Override
    public void create(Appointment appointment, HttpServletRequest request) {
        String username = appointment.getUsername();
        String visitTime = appointment.getDateTime();
        User user = userService.get(username);
        User doctor = (User) request.getSession().getAttribute(CURRENT_USER);
        this.add(user.getId(), visitTime, doctor.getDoctorId());
    }

    @Override
    public void delete(Appointment appointment, HttpServletRequest request) {
        String visitTime = appointment.getDateTime();
        LocalDateTime visitDateTime;

        try {
            visitDateTime = LocalDateTime.parse(visitTime, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Entered time is wrong!");
        }
        User doctor = (User) request.getSession().getAttribute(CURRENT_USER);
        Talon talon = repository.getTalonForDeletion(doctor.getDoctorId(), visitDateTime);

        if (talon != null) {
            repository.delete(talon);
        } else {
            throw new IllegalArgumentException("Talon isn't exist!");
        }
    }
}
