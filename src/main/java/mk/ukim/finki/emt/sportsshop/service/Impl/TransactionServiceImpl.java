package mk.ukim.finki.emt.sportsshop.service.Impl;

import mk.ukim.finki.emt.sportsshop.models.Transactions;
import mk.ukim.finki.emt.sportsshop.repository.TransactionRepository;
import mk.ukim.finki.emt.sportsshop.service.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {


    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transactions addNewTransaction(Transactions transactions) {
        return transactionRepository.save(transactions);
    }
}
