package org.archivos.ecommercegt.services;

import jakarta.transaction.Transactional;
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
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ShoppingCartService shoppingCartService;

    @Transactional
    public PurchaseDetail save(PurchaseDetailRequest request) {

        // validar cantidad
        if (request.getAmount() < 0) throw new  ResponseStatusException(HttpStatus.BAD_REQUEST, "La cantidad es invalida");

        // obtener el carrito de compras
        ShoppingCart shoppingCart = shoppingCartService.getCurrentShoppingCart();

        // obtener el producto
        Product product = productRepository
                .findById(request.getProductId())
                .orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Producto no encontrado"));

        // validar stock
        if(product.getStock() < request.getAmount()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock insuficiente");

        // actualizar stock
        product.setStock(product.getStock() - request.getAmount());
        productRepository.save(product);

        PurchaseDetail purchaseDetail = new PurchaseDetail();
        purchaseDetail.setProduct(product);
        purchaseDetail.setShoppingCart(shoppingCart);
        purchaseDetail.setAmount(request.getAmount());

        return purchaseDetailRepository.save(purchaseDetail);
    }
}
