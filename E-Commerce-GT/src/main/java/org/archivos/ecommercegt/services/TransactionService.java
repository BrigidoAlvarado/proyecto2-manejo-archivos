package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.models.Transaction;
import org.archivos.ecommercegt.models.Wallet;
import org.archivos.ecommercegt.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public Transaction save(double amount, Wallet wallet) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setWallet(wallet);
        return transactionRepository.save(transaction);
    }
}
