package storage.api;

import model.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPasswordRepository extends JpaRepository<Password, String> {

    Password getByLogin(String login);
}
