package view;

import model.Talon;
import storage.api.ITalonRepository;
import view.api.ITalonService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TalonService implements ITalonService {
    private final ITalonRepository repository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public TalonService(ITalonRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(Integer userId, String visitTimeString, Integer doctorId) {
        LocalDateTime visitTime = LocalDateTime.parse(visitTimeString, formatter);
        Talon talon = new Talon(userId, visitTime, doctorId);
        repository.save(talon);
    }
}
