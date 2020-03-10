package mk.ukim.finki.emt.sportsshop.repository;


import mk.ukim.finki.emt.sportsshop.models.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersistanceProductRepositoryTest {

@Autowired
    private NewProductRepository repo;

@Before
    public void init(){
    Product p1 = new Product();
    p1.setName("Sleeves");
    p1.setId(1L);
    p1.setDescription("description");

    repo.save(p1);

    Product p2 = new Product();
    p2.setName("Trainers");
    p2.setId(2L);
    p2.setDescription("description number2");

    repo.save(p2);

}

@Test
    public void getAll(){
    List<Product> productList = repo.findAll();
    Assert.assertEquals(2L, productList.size());
}

}
