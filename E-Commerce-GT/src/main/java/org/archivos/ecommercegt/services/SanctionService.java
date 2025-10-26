package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.dto.sanction.SanctionDeliveryPackageRequest;
import org.archivos.ecommercegt.dto.sanction.SanctionProductRequest;
import org.archivos.ecommercegt.dto.sanction.SanctionResponse;
import org.archivos.ecommercegt.models.*;
import org.archivos.ecommercegt.repository.SanctionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SanctionService {

    private final SanctionRepository sanctionRepository;
    private final DeliveryPackageService deliveryPackageService;

    private final UserService userService;
    private final ProductService productService;

    @Transactional
    public void sanctionAndRejectProduct(SanctionProductRequest sanctionRequest) {

        Product product = productService.getProductById( sanctionRequest.getApproveProductRequest().getId() );
        productService.approveProduct(sanctionRequest.getApproveProductRequest());

        Sanction sanction = new Sanction();
        sanction.setUser(product.getUser());
        sanction.setReason(sanctionRequest.getReason());
        sanction.setCreatedAt( Instant.now());
        sanction.setEndAt( Instant.now().plus(sanctionRequest.getAmountDays(), ChronoUnit.DAYS) );

        sanctionRepository.save(sanction);
    }

    @Transactional
    public void sanctionDeliveryPackage(SanctionDeliveryPackageRequest request){

        Sanction sanction = new Sanction();
        sanction.setCreatedAt( Instant.now() );
        sanction.setEndAt( Instant.now().plus( request.getAmountDays(), ChronoUnit.DAYS ));
        sanction.setReason( request.getReason() );

        final DeliveryPackage deliveryPackage = deliveryPackageService.getDeliveryPackageById( request.getDeliveryPackageId() );

        for( PurchaseDetail item: deliveryPackage.getShoppingCart().getPurchaseDetails() ){
            User user = item.getProduct().getUser();
            sanction.setUser(user);
            sanctionRepository.save(sanction);
        }

        deliveryPackage.setIsRevised(true);
        deliveryPackageService.save(deliveryPackage);
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

    public List<SanctionResponse> getSanctionsByUserId(int userId){
        final List<Sanction> sanctions = sanctionRepository.findByUserId( userId );
        final List<SanctionResponse> sanctionsResponse = new ArrayList<>();

        for (Sanction sanction : sanctions) {
            sanctionsResponse.add(
              SanctionResponse.builder()
                      .endAt( sanction.getEndAt() )
                      .createdAt( sanction.getCreatedAt() )
                      .reason( sanction.getReason() )
                      .build()
            );
        }
        return sanctionsResponse;
    }
}
