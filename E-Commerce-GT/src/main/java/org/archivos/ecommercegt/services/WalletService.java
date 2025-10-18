package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.config.ApplicationConfig;
import org.archivos.ecommercegt.models.User;
import org.archivos.ecommercegt.models.Wallet;
import org.archivos.ecommercegt.repository.WalletRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

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

    public void updateMoney(User user, double amount) {
        Wallet wallet = walletRepository
                .findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        updateMoney(amount, wallet);
    }

    private void updateMoney(double amount, Wallet wallet) {
        wallet.setMoney(wallet.getMoney() + amount);
        walletRepository.save(wallet);
    }
}
