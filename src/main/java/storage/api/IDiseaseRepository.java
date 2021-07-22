package storage.api;

import model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDiseaseRepository extends JpaRepository<Disease, Integer> {
}
