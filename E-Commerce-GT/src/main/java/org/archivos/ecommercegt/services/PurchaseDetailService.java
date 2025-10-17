package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.dto.purchaseDetail.PurchaseDetailRequest;
import org.archivos.ecommercegt.models.Product;
import org.archivos.ecommercegt.models.PurchaseDetail;
import org.archivos.ecommercegt.models.ShoppingCart;
import org.archivos.ecommercegt.models.User;
import org.archivos.ecommercegt.repository.ProductRepository;
import org.archivos.ecommercegt.repository.PurchaseDetailRepository;
import org.archivos.ecommercegt.repository.ShoppingCartRepository;
import org.archivos.ecommercegt.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PurchaseDetailService {

    private final PurchaseDetailRepository purchaseDetailRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public PurchaseDetail save(PurchaseDetailRequest request) {

        // validar cantidad
        if (request.getAmount() < 0) throw new  ResponseStatusException(HttpStatus.BAD_REQUEST, "La cantidad es invalida");

        // obtener el carrito de compras
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository
                .findByEmail(authentication.getName())
                .orElseThrow(() -> new  ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        ShoppingCart shoppingCart = shoppingCartRepository
                .findByStatusAndUser(true, user).get();

        if(shoppingCart == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrito no encontrado");

        // obtener el producto
        Product product = productRepository
                .findById(request.getProductId())
                .orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Producto no encontrado"));

        // validar stock
        if(product.getStock() < request.getAmount()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock insuficiente");

        PurchaseDetail purchaseDetail = new PurchaseDetail();
        purchaseDetail.setProduct(product);
        purchaseDetail.setShoppingCart(shoppingCart);
        purchaseDetail.setAmount(request.getAmount());

        return purchaseDetailRepository.save(purchaseDetail);
    }
}
