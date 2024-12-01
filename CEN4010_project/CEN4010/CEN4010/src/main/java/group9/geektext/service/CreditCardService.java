package group9.geektext.service;

import java.util.List;

import org.springframework.stereotype.Service;

import group9.geektext.entity.CreditCard;
import group9.geektext.repository.CreditCardRepository;

@Service
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;

    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    // Get all credit cards for a user
    public List<CreditCard> getCreditCardsByUser(Long userId) {
        return creditCardRepository.findByUser_Id(userId);
    }

    // Save a new credit card
    public CreditCard createCreditCard(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    // Update a credit card
    public CreditCard updateCreditCard(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    // Delete a credit card
    public void deleteCreditCard(Long creditCardId) {
        creditCardRepository.deleteById(creditCardId);
    }
}
