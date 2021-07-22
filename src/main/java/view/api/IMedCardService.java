package view.api;


public interface IMedCardService {
    void add (Integer cardId, Integer doctorId, String diseaseName, String diagnoseDate, String dischargeDate);
}
