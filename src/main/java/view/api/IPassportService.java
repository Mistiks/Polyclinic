package view.api;

import model.enums.Gender;

public interface IPassportService {

    void add(int userId, String passportId, String passportNum, String country, String nationality, String birthDate,
             Gender sex, String issueDate, String expireDate, String birth);
}
