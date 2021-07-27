package storage.api;

import model.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPassportRepository extends JpaRepository<Passport, Integer> {

    Passport getByUserId(Integer id);
}
