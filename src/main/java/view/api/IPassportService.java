package view.api;

import java.time.LocalDate;

public interface IPassportService {

    void add(int userId, String passportId, String passportNum, String country, String nationality, String birthDate,
             String sex, String issueDate, String expireDate, String birth);
}
