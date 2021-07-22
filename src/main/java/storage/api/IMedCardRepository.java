package storage.api;

import model.MedCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedCardRepository extends JpaRepository<MedCard, Integer> {
}
