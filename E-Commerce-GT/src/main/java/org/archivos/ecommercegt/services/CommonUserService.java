package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.models.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommonUserService {

    private final ShoppingCartService  shoppingCartService;
    private final WalletService walletService;

    public void initUser(User user){
        shoppingCartService.save(user);
        walletService.save(user);
    }
}
