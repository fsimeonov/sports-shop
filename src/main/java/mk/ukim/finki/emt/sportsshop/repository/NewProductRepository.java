package mk.ukim.finki.emt.sportsshop.repository;

import mk.ukim.finki.emt.sportsshop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewProductRepository extends JpaRepository<Product,Long> {
     List<Product> findByCategory_Id(Long id);
     List<Product> findAllByCategory_IdAndManufacturer_Id(Long categoryId, Long manufacturerId);
     Product getById(Long id);

    @Query(value = "select sum(p.price) from Product p where p.category.id=:categoryId")
    Double getSum(@Param("categoryId") Long categoryId);



}

