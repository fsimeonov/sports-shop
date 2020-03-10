package mk.ukim.finki.emt.sportsshop.repository;

import mk.ukim.finki.emt.sportsshop.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transactions,Long> {

}
