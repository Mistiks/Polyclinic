package view;

import model.Passport;
import model.enums.Gender;
import storage.api.IPassportRepository;
import view.api.IPassportService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PassportService implements IPassportService {

    private final IPassportRepository repository;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public PassportService(IPassportRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(int userId, String passportId, String passportNum, String country, String nationality, String birthDateString,
                    Gender sex, String issueDateString, String expireDateString, String birth) {
        LocalDate birthDate = LocalDate.parse(birthDateString, formatter);
        LocalDate issueDate = LocalDate.parse(issueDateString, formatter);
        LocalDate expireDate = LocalDate.parse(expireDateString, formatter);
        Passport passport = new Passport(userId, passportId, passportNum, country, nationality, birthDate, sex, issueDate, expireDate, birth);
        repository.save(passport);
    }
}
