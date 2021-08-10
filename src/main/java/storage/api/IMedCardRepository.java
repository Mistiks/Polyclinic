package storage.api;

import model.MedCard;
import model.dto.MedCardWithUsername;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IMedCardRepository extends JpaRepository<MedCard, Integer> {

    @Query(
            value = "SELECT card FROM MedCard card " +
                    "WHERE card.cardId = ?1 and card.doctorId = ?2 and card.diagnoseDate = ?3")
    MedCard getMedCardForDeleteOrUpdate(Integer userId, Integer doctorId, LocalDate date);

    @Query(
            value = "SELECT new model.dto.MedCardWithUsername(u.login, card.diseaseName, card.diagnoseDate, card.dischargeDate, card.notes) FROM MedCard card " +
                    "inner join User u ON card.cardId = u.id WHERE card.doctorId = ?1 ORDER BY card.diagnoseDate")
    List<MedCardWithUsername> getAllRecordsForDoctor(Integer doctorId);

    @Query(
            value = "SELECT new model.dto.MedCardWithUsername(u.login, card.diseaseName, card.diagnoseDate, card.dischargeDate, card.notes) FROM MedCard card " +
                    "inner join User u ON card.doctorId = u.doctorId WHERE card.cardId = ?1 ORDER BY card.diagnoseDate")
    List<MedCardWithUsername> findByUserIdOrderByDiagnoseDate(Integer userId);

    List<MedCard> findAllByUserId(Integer userId);
}
