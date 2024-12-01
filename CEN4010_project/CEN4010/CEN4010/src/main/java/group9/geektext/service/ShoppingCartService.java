package group9.geektext.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import group9.geektext.entity.ShoppingCart;
import group9.geektext.repository.ShoppingCartRepository;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    // Get shopping cart by user ID
    public Optional<ShoppingCart> getShoppingCartByUser(Long userId) {
        return shoppingCartRepository.findByUser_Id(userId);
    }

    // Create a new shopping cart
    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    // Update an existing shopping cart
    public ShoppingCart updateShoppingCart(ShoppingCart updatedCart) {
        return shoppingCartRepository.save(updatedCart);  // save() will either create or update
    }

    // Delete a shopping cart by ID
    public void deleteShoppingCart(Long cartId) {
        shoppingCartRepository.deleteById(cartId);
    }
}
