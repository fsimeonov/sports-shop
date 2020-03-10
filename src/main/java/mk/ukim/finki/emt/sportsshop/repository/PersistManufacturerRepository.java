package mk.ukim.finki.emt.sportsshop.repository;

import mk.ukim.finki.emt.sportsshop.models.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface PersistManufacturerRepository extends JpaRepository<Manufacturer,Long> {

}
