package storage.api;

import model.Talon;
import model.dto.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ITalonRepository extends JpaRepository<Talon, Integer> {

    @Query(
            value = "SELECT DISTINCT ON(date(talon.visit_time)) * FROM polyclinic.talon WHERE " +
                    "talon.doctor_id = ?1 and talon.user_id IS NULL ORDER BY date(talon.visit_time)",
            nativeQuery = true)
    List<Talon> findAllByFio(Integer doctorId);

    @Query(
            value = "SELECT * FROM polyclinic.talon " +
                    "WHERE talon.doctor_id = ?1 and date(talon.visit_time) = ?2 and talon.user_id IS NULL ORDER BY talon.visit_time",
            nativeQuery = true)
    List<Talon> findAllByTalonDate(Integer id, LocalDate date);

    @Query(
            value = "SELECT * FROM polyclinic.talon WHERE talon.user_id = ?1",
            nativeQuery = true)
    List<Talon> findAllByUserId(Integer userId);

    @Query(
            value = "SELECT * FROM polyclinic.talon " +
                    "WHERE talon.doctor_id = ?1 and talon.visit_time = ?2 and talon.user_id IS NULL ORDER BY talon.visit_time",
            nativeQuery = true)
    Talon getTalon(Integer id, LocalDateTime dateTime);

    @Query(
            value = "SELECT * FROM polyclinic.talon " +
                    "WHERE talon.doctor_id = ?1 and talon.visit_time = ?2",
            nativeQuery = true)
    Talon getTalonForDeleteOrUpdate(Integer id, LocalDateTime dateTime);

    @Query(
            value = "SELECT * FROM polyclinic.talon " +
                    "WHERE talon.doctor_id = ?1 and talon.visit_time = ?2 and talon.user_id IS NOT NULL",
            nativeQuery = true)
    Talon isTalonBooked(Integer id, LocalDateTime dateTime);

    @Query(
            value = "SELECT new model.dto.Appointments(u.login, t.visitTime) FROM Talon t " +
                    "inner join User u ON u.id = t.userId WHERE t.doctorId = ?1 and t.userId IS NOT NULL")
    List<Appointments> findByDoctorIdOrderByVisitTime(Integer doctorId);

    @Query(
            value = "SELECT new model.dto.Appointments(u.login, t.visitTime) FROM Talon t " +
                    "inner join User u ON u.doctorId = t.doctorId WHERE t.userId = ?1")
    List<Appointments> findByUserIdOrderByVisitTime(Integer userId);
}
