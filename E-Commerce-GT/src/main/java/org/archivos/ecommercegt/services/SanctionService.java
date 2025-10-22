package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.dto.SanctionRequest;
import org.archivos.ecommercegt.models.Product;
import org.archivos.ecommercegt.models.Sanction;
import org.archivos.ecommercegt.repository.SanctionRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class SanctionService {

    private final SanctionRepository sanctionRepository;

    private final UserService userService;
    private final ProductService productService;

    public Sanction save(SanctionRequest  sanctionRequest) {

        Product product = productService.getProductById( sanctionRequest.getApproveProductRequest().getId() );

        productService.approveProduct(sanctionRequest.getApproveProductRequest());

        Sanction sanction = new Sanction();
        sanction.setUser(product.getUser());
        sanction.setReason(sanctionRequest.getReason());
        sanction.setCreatedAt( Instant.now());
        sanction.setEndAt( Instant.now().plus(sanctionRequest.getAmountDays(), ChronoUnit.DAYS) );

        return sanctionRepository.save(sanction);
    }


}
