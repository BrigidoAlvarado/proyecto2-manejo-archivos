package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.dto.card.CreditCardResponse;
import org.archivos.ecommercegt.models.CreditCard;
import org.archivos.ecommercegt.models.User;
import org.archivos.ecommercegt.repository.CreditCardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Credit card service.
 */
@Service
@RequiredArgsConstructor
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final UserService userService;

    /**
     * Valid number.
     *
     * @param number the number
     */
    public void validNumber(String number) {
        if (number.length() > 20){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credit card number is too long");
        }

        if (!number.matches("\\d+")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El numero de tarjeta es invalido");
        }
    }

    /**
     * Save credit card.
     *
     * @param number the number
     * @param user   the user
     * @return the credit card
     */
    public CreditCard save(String number, User user) {

        validNumber(number);

        CreditCard creditCard = new CreditCard();
        creditCard.setNumber(number);
        creditCard.setUser(user);
        return creditCardRepository.save(creditCard);
    }

    /**
     * Find all by user list.
     *
     * @return the list
     */
    public List<CreditCardResponse> findAllByUser() {
        List<CreditCard> cardsFound = creditCardRepository.findAllByUser((userService.getUser()));
        List<CreditCardResponse> creditCardResponses = new ArrayList<>();

        for (CreditCard creditCard : cardsFound) {
            creditCardResponses.add(
              CreditCardResponse.builder()
                      .id( creditCard.getId() )
                      .cardNumber( creditCard.getNumber() ).build()
            );
        }

        return creditCardResponses;
    }
}
