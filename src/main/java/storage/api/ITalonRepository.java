package storage.api;

import model.Talon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITalonRepository extends JpaRepository<Talon, Integer> {
}
