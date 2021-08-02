package storage.api;

import model.Talon;
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
                    "WHERE talon.doctor_id = ?1 and date(talon.visit_time) = ?2 ORDER BY talon.visit_time",
            nativeQuery = true)
    List<Talon> findAllByTalonDate(Integer id, LocalDate date);

    @Query(
            value = "SELECT * FROM polyclinic.talon WHERE talon.user_id = ?1",
            nativeQuery = true)
    List<Talon> findAllByUserId(Integer userId);

    @Query(
            value = "SELECT * FROM polyclinic.talon " +
                    "WHERE talon.doctor_id = ?1 and talon.visit_time = ?2 ORDER BY talon.visit_time",
            nativeQuery = true)
    Talon getTalon(Integer id, LocalDateTime dateTime);
}
