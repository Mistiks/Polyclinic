package storage.api;

import model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}
