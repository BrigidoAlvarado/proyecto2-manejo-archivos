package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.dto.qualification.QualificationRequest;
import org.archivos.ecommercegt.models.Product;
import org.archivos.ecommercegt.models.Qualification;
import org.archivos.ecommercegt.models.User;
import org.archivos.ecommercegt.repository.QualificationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QualificationService {

    private final QualificationRepository qualificationRepository;

    private final UserService userService;
    private final ProductService productService;

    public Qualification save (QualificationRequest request){

        final User user = userService.getUser();
        final Product product = productService.getProductById(request.getProductId());

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
}
