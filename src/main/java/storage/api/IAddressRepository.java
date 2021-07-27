package storage.api;

import model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Integer> {

    /*@Query(value = "SELECT * FROM polyclinic.addresses adr WHERE adr.user_id = :id",
    nativeQuery = true)*/
    Address getByUserId(/*@Param("id")*/ Integer id);
}
