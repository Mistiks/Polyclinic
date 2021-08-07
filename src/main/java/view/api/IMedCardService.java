package view.api;

import model.dto.MedCardWithUsername;
import model.dto.MedcardDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IMedCardService {
    void add (Integer cardId, Integer doctorId, String diseaseName, String diagnoseDate, String dischargeDate, String notes);
    void create (MedcardDTO medcardDTO, HttpServletRequest request);
    void delete (MedcardDTO medcardDTO, HttpServletRequest request);
    void update (MedcardDTO medcardDTO);
    MedcardDTO getMedcardRecord(MedcardDTO diseaseDTO, HttpServletRequest request);

    List<MedCardWithUsername> getAllRecords(HttpServletRequest request);
}