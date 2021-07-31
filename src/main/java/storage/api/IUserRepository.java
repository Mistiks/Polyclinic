package storage.api;

import model.Talon;
import model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    User getByLogin(String login);
    User getByGoogleId(String id);

    @Query(
            value = "SELECT DISTINCT user_position FROM polyclinic.users WHERE user_position IS NOT NULL",
            nativeQuery = true)
    List<String> findAllPositions();

    @Query(
            value = "SELECT * FROM polyclinic.users WHERE user_position = ?1",
            nativeQuery = true)
    List<User> findAllByPosition(String position);

    @Query(
            value = "SELECT * FROM polyclinic.users WHERE user_position = ?1 and surname = ?2 " +
                    "and first_name = ?3 and patronymic = ?4",
            nativeQuery = true)
    User getUserByDoctorId(String position, String surname, String firstName, String patronymic);

    @Query(
            value = "SELECT talon.talon_id FROM polyclinic.talon " +
                    "WHERE talon.doctor_id = ?1 and to_char(talon.visit_time, 'YYYY-MM-DD HH24:MI') = ?2",
            nativeQuery = true)
    Integer getTalonId(Integer id, String date);
}
