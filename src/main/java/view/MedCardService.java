package view;

import model.MedCard;
import model.User;
import model.dto.Appointments;
import model.dto.MedCardWithUsername;
import model.dto.MedcardDTO;
import model.dto.UserSession;
import storage.api.IMedCardRepository;
import view.api.IMedCardService;
import view.api.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class MedCardService implements IMedCardService {
    private final IMedCardRepository repository;
    private final IUserService userService;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final String CURRENT_USER = "currentUser";

    public MedCardService(IMedCardRepository repository, IUserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public void add(Integer cardId, Integer doctorId, String diseaseName,
                    String diagnoseDateString, String dischargeDateString, String notes) {
        LocalDate diagnoseDate = LocalDate.parse(diagnoseDateString, formatter);
        LocalDate dischargeDate = LocalDate.parse(dischargeDateString, formatter);
        MedCard medcard = new MedCard(cardId, doctorId, diseaseName, diagnoseDate, dischargeDate, notes);
        repository.save(medcard);
    }

    @Override
    public void create(MedcardDTO medcardDTO, HttpServletRequest request) {
        User user = userService.get(medcardDTO.getUsername());
        UserSession doctor = (UserSession) request.getSession().getAttribute(CURRENT_USER);
        LocalDate diagnoseDate;
        LocalDate dischargeDate;

        if (user == null) {
            throw new IllegalArgumentException("User isn't exist!");
        }

        diagnoseDate = checkAndParseDate(medcardDTO.getDiagnoseDate());

        if (diagnoseDate == null) {
            throw new IllegalArgumentException("Diagnose date couldn't be empty!");
        }

        dischargeDate = checkAndParseDate(medcardDTO.getDischargeDate());

        MedCard medCard = new MedCard(user.getId(), doctor.getDoctorId(), medcardDTO.getDiseaseName(),
                diagnoseDate, dischargeDate, medcardDTO.getNotes());
        repository.save(medCard);
    }

    @Override
    public void delete(MedcardDTO medcardDTO, HttpServletRequest request) {
        User user = userService.get(medcardDTO.getUsername());
        UserSession doctor = (UserSession) request.getSession().getAttribute(CURRENT_USER);
        LocalDate diagnoseDate;

        if (user == null) {
            throw new IllegalArgumentException("User isn't exist!");
        }

        diagnoseDate = checkAndParseDate(medcardDTO.getDiagnoseDate());

        if (diagnoseDate == null) {
            throw new IllegalArgumentException("Diagnose date couldn't be empty!");
        }

        MedCard medCard = repository.getMedCardForDeleteOrUpdate(user.getId(), doctor.getDoctorId(), diagnoseDate);
        repository.delete(medCard);
    }

    @Override
    public MedcardDTO getMedcardRecord(MedcardDTO diseaseDTO, HttpServletRequest request) {
        User user = userService.get(diseaseDTO.getUsername());
        UserSession doctor = (UserSession) request.getSession().getAttribute(CURRENT_USER);
        LocalDate diagnoseDate;

        if (user == null) {
            throw new IllegalArgumentException("User isn't exist!");
        }

        diagnoseDate = checkAndParseDate(diseaseDTO.getDiagnoseDate());

        if (diagnoseDate == null) {
            throw new IllegalArgumentException("Diagnose date couldn't be empty!");
        }

        MedCard medCard = repository.getMedCardForDeleteOrUpdate(user.getId(), doctor.getDoctorId(), diagnoseDate);

        if (medCard == null) {
            throw new IllegalArgumentException("Record doesn't exist!");
        }
        return new MedcardDTO(medCard.getId(), user.getLogin(), medCard.getDiseaseName(),
                medCard.getDiagnoseDate().format(formatter), medCard.getDischargeDate().format(formatter), medCard.getNotes());
    }

    @Override
    @Transactional
    public void update(MedcardDTO medcardDTO) {
        MedCard medCard = repository.getById(medcardDTO.getMedcardId());
        User user = userService.get(medcardDTO.getUsername());
        LocalDate diagnoseDate;
        LocalDate dischargeDate;

        if (user == null) {
            throw new IllegalArgumentException("User isn't exist!");
        }

        diagnoseDate = checkAndParseDate(medcardDTO.getDiagnoseDate());

        if (diagnoseDate == null) {
            throw new IllegalArgumentException("Diagnose date couldn't be empty!");
        }

        dischargeDate = checkAndParseDate(medcardDTO.getDischargeDate());
        medCard.setCardId(user.getId());
        medCard.setDiseaseName(medcardDTO.getDiseaseName());
        medCard.setDiagnoseDate(diagnoseDate);
        medCard.setDischargeDate(dischargeDate);
        medCard.setNotes(medcardDTO.getNotes());
        repository.save(medCard);
    }

    @Override
    @Transactional
    public List<MedCardWithUsername> getAllRecords(HttpServletRequest request) {
        UserSession doctor = (UserSession) request.getSession().getAttribute(CURRENT_USER);
        return repository.getAllRecordsForDoctor(doctor.getDoctorId());
    }

    /**
     * Transforms String in LocalDate if possible
     *
     * @param date String containing date
     * @return LocalDate of String or null if String empty
     * @throws IllegalArgumentException if parsing is impossible
     */
    private LocalDate checkAndParseDate(String date) {
        LocalDate result = null;

        if (!date.isEmpty()) {
            try {
                result = LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Date is wrong!");
            }
        }

        return result;
    }
}
