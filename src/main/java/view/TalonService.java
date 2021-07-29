package view;

import model.Talon;
import model.enums.TalonTime;
import storage.api.ITalonRepository;
import view.api.ITalonService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TalonService implements ITalonService {
    private final ITalonRepository repository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public TalonService(ITalonRepository repository) {
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

        Talon talon = new Talon(userId, visitTime, doctorId);
        repository.save(talon);
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
}
