package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.config.ApplicationConfig;
import org.archivos.ecommercegt.models.User;
import org.archivos.ecommercegt.models.Wallet;
import org.archivos.ecommercegt.repository.WalletRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    private final TransactionService transactionService;

    private Wallet getAppWallet() {
        return walletRepository
                .findByUser(User.builder().email(ApplicationConfig.ADMIN_EMAIL).build())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public Wallet save(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setMoney(0.0);
        return walletRepository.save(wallet);
    }

    public void updateAppMoney(double money) {
        updateMoney(money, getAppWallet());
    }

    @Transactional
    public void updateMoney(User user, double amount) {
        Wallet wallet = walletRepository
                .findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        // crear una nueva transaccion
        transactionService.save(amount, wallet);
        // actualizar la cantidad de dinero
        updateMoney(amount, wallet);
    }

    private void updateMoney(double amount, Wallet wallet) {
        wallet.setMoney(wallet.getMoney() + amount);
        walletRepository.save(wallet);
    }
}
