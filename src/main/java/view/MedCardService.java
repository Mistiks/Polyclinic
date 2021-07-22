package view;

import model.MedCard;
import storage.api.IMedCardRepository;
import view.api.IMedCardService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MedCardService implements IMedCardService {
    private final IMedCardRepository repository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public MedCardService(IMedCardRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(Integer cardId, Integer doctorId, String diseaseName,
                    String diagnoseDateString, String dischargeDateString) {
        LocalDate diagnoseDate = LocalDate.parse(diagnoseDateString, formatter);
        LocalDate dischargeDate = LocalDate.parse(dischargeDateString, formatter);
        MedCard medcard = new MedCard(cardId, doctorId, diseaseName, diagnoseDate, dischargeDate);
        repository.save(medcard);
    }
}
