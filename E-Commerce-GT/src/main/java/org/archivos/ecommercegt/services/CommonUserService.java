package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.models.User;
import org.springframework.stereotype.Service;

/**
 * The type Common user service.
 */
@Service
@RequiredArgsConstructor
public class CommonUserService {

    private final ShoppingCartService  shoppingCartService;
    private final WalletService walletService;

    /**
     * Init user.
     *
     * @param user the user
     */
    public void initUser(User user){
        shoppingCartService.save(user);
        walletService.save(user);
    }
}
