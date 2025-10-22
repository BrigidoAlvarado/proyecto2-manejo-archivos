package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.dto.SanctionRequest;
import org.archivos.ecommercegt.models.Product;
import org.archivos.ecommercegt.models.Sanction;
import org.archivos.ecommercegt.models.User;
import org.archivos.ecommercegt.repository.SanctionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SanctionService {

    private final SanctionRepository sanctionRepository;

    private final UserService userService;
    private final ProductService productService;

    @Transactional
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

    public void validateSanction(User user){
        Optional<Sanction> sanction = sanctionRepository
                .findByUserAndEndAtGreaterThan(
                        user,
                        Instant.now()
                );
        if (sanction.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Usuario sancionado hasta: " + sanction.get().getEndAt().toString()
            );
        }
    }
}
