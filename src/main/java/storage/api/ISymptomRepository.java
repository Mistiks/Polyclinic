package storage.api;

import model.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISymptomRepository extends JpaRepository<Symptom, Integer> {
}
