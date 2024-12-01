package group9.geektext.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group9.geektext.entity.CreditCard;
import group9.geektext.service.CreditCardService;

@RestController
@RequestMapping("/api/credit-cards")
public class CreditCardController {

    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    // Get all credit cards for a user
    @GetMapping("/user/{userId}")
    public List<CreditCard> getCreditCardsByUser(@PathVariable Long userId) {
        return creditCardService.getCreditCardsByUser(userId);
    }

    // Add a new credit card
    @PostMapping
    public ResponseEntity<CreditCard> createCreditCard(@RequestBody CreditCard creditCard) {
        return ResponseEntity.ok(creditCardService.createCreditCard(creditCard));
    }

    // Delete a credit card
    @DeleteMapping("/{creditCardId}")
    public ResponseEntity<Void> deleteCreditCard(@PathVariable Long creditCardId) {
        creditCardService.deleteCreditCard(creditCardId);
        return ResponseEntity.noContent().build();
    }
}
