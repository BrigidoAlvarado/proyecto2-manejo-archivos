package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.models.CreditCard;
import org.archivos.ecommercegt.models.User;
import org.archivos.ecommercegt.repository.CreditCardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;

    public void validNumber(String number) {
        if (number.length() > 20){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credit card number is too long");
        }

        if (!number.matches("\\d+")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El numero de tarjeta es invalido");
        }
    }

    public CreditCard save(String number, User user) {

        validNumber(number);

        CreditCard creditCard = new CreditCard();
        creditCard.setNumber(number);
        creditCard.setUser(user);
        return creditCardRepository.save(creditCard);
    }
}
