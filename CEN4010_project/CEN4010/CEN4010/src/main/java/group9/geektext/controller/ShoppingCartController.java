package group9.geektext.controller;

import group9.geektext.entity.ShoppingCart;
import group9.geektext.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    // Get the shopping cart for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<ShoppingCart> getShoppingCartByUser(@PathVariable Long userId) {
        Optional<ShoppingCart> cart = shoppingCartService.getShoppingCartByUser(userId);
        return cart.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Add a new shopping cart
    @PostMapping
    public ResponseEntity<ShoppingCart> createShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        return ResponseEntity.ok(shoppingCartService.createShoppingCart(shoppingCart));
    }

    // Update the shopping cart
    @PutMapping("/{cartId}")
    public ResponseEntity<ShoppingCart> updateShoppingCart(@PathVariable Long cartId, @RequestBody ShoppingCart updatedCart) {
        updatedCart.setId(cartId);
        return ResponseEntity.ok(shoppingCartService.updateShoppingCart(updatedCart));
    }

    // Delete a shopping cart
    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteShoppingCart(@PathVariable Long cartId) {
        shoppingCartService.deleteShoppingCart(cartId);
        return ResponseEntity.noContent().build();
    }
}
