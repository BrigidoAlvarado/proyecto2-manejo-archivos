package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.dto.qualification.QualificationRequest;
import org.archivos.ecommercegt.models.Product;
import org.archivos.ecommercegt.models.Qualification;
import org.archivos.ecommercegt.models.User;
import org.archivos.ecommercegt.repository.ProductRepository;
import org.archivos.ecommercegt.repository.QualificationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * The type Qualification service.
 */
@Service
@RequiredArgsConstructor
public class QualificationService {

    private final QualificationRepository qualificationRepository;
    private final ProductRepository productRepository ;


    private final UserService userService;

    /**
     * Save qualification.
     *
     * @param request the request
     * @return the qualification
     */
    public Qualification save (QualificationRequest request){

        final User user = userService.getUser();
        final Product product = getProductById(request.getProductId());

        //validar si ya existe
        Qualification qualification = qualificationRepository.findByUserAndProduct(
                user,
                product
        );

        if(qualification == null)  {
            qualification = new Qualification();
            qualification.setUser(user);
            qualification.setProduct(product);
        }

        qualification.setStarts(request.getStarts());
        return qualificationRepository.save(qualification);
    }

    /**
     * Calculate qualification int.
     *
     * @param productId the product id
     * @return the int
     */
    public int calculateQualification( int productId ){
        final List<Qualification> qualifications = qualificationRepository
                .findByProduct( getProductById( productId ) );

        int sum = 0;
        for (Qualification qualification : qualifications) {
            sum += qualification.getStarts();
        }

        if (sum == 0) return 0;

        return sum / qualifications.size();
    }

    private Product getProductById(int id){
        return productRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado")
                );
    }
}
