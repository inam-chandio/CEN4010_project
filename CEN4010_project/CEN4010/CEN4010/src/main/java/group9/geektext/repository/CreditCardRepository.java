package group9.geektext.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import group9.geektext.entity.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    List<CreditCard> findByUser_Id(Long userId);  // Find credit cards by user ID
}
