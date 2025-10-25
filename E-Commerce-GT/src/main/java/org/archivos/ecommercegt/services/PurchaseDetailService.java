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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseDetailService {
    public static final String DUPLICATE_KEY = "23505";

    private final PurchaseDetailRepository purchaseDetailRepository;
    private final ShoppingCartService shoppingCartService;

    private final ProductService productService;

    @Transactional
    public void deletePurchaseDetail( int id){
        PurchaseDetail purchaseDetail = purchaseDetailRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Detalle del producto no encontrado en el carrito"));

        // update stock
        Product product = purchaseDetail.getProduct();
        productService.updateStock( product, product.getStock() + purchaseDetail.getAmount() );

        // delete purchase detail
        purchaseDetailRepository.delete(purchaseDetail);
    }

    @Transactional
    public PurchaseDetail save(PurchaseDetailRequest request) {

        // validar cantidad
        if (request.getAmount() < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La cantidad es invalida");

        // obtener el carrito de compras
        ShoppingCart shoppingCart = shoppingCartService.getCurrentShoppingCart();

        // obtener el producto
        Product product = productService.getProductById(request.getProductId());

        // validar si ya existe un purchase detail con este producto y este carrito
        Optional<PurchaseDetail> optionalPurchaseDetail = getPurchaseDetailByProductAndShoppingCart( product, shoppingCart);

        // create a new purchase detail
       if (optionalPurchaseDetail.isEmpty()) {
           return createPurchaseDetail(request, product, shoppingCart);
       }

       // add amount to purchase detail
       if (request.getPurchaseDetailId() == null ){
          return addToPurchaseDetail(request, optionalPurchaseDetail.get(), product);
       }
       // calculate if add or substring amount to purchase detail
       else {
           return updatePurchaseDetail(request, optionalPurchaseDetail.get(), product);
       }
    }

    private PurchaseDetail updatePurchaseDetail(
            PurchaseDetailRequest request,
            PurchaseDetail purchaseDetail,
            Product product
    ) {

        final int oldAmount = purchaseDetail.getAmount();
        final int newAmount = request.getAmount();
        final int result = oldAmount - newAmount;

        // Update stock
        product.setStock(product.getStock() + result);
        productService.save(product);

        // Update purchase detail
        purchaseDetail.setAmount(newAmount);
        return purchaseDetailRepository.save(purchaseDetail);
    }

    private PurchaseDetail addToPurchaseDetail(
            PurchaseDetailRequest request,
            PurchaseDetail purchaseDetail,
            Product product
    ) {
        // Update stock in product
        final int newStock = product.getStock() - request.getAmount();
        productService.updateStock(product, newStock);

        // Update purchase amount in detail
        purchaseDetail.setAmount(purchaseDetail.getAmount() + request.getAmount());
        return purchaseDetailRepository.save(purchaseDetail);
    }

    private PurchaseDetail createPurchaseDetail(
            PurchaseDetailRequest request,
            Product product,
            ShoppingCart shoppingCart
    ) {
        // validar stock
        if (product.getStock() < request.getAmount())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock insuficiente");

        // actualizar stock
        product.setStock(product.getStock() - request.getAmount());
        productService.save(product);

        PurchaseDetail purchaseDetail = new PurchaseDetail();
        purchaseDetail.setProduct(product);
        purchaseDetail.setShoppingCart(shoppingCart);
        purchaseDetail.setAmount(request.getAmount());

        return purchaseDetailRepository.save(purchaseDetail);
    }


    private Optional<PurchaseDetail> getPurchaseDetailByProductAndShoppingCart(Product product, ShoppingCart shoppingCart) {
        return purchaseDetailRepository.findByProductAndShoppingCart(product, shoppingCart);
    }
}
