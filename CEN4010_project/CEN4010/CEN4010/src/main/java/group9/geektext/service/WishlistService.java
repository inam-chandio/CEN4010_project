package group9.geektext.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import group9.geektext.entity.Wishlist;
import group9.geektext.repository.WishlistRepository;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    // Get all wishlists for a user
    public List<Wishlist> getWishlistsByUser(Long userId) {
        return wishlistRepository.findByUser_Id(userId);
    }

    // Get a specific wishlist by ID
    public Optional<Wishlist> getWishlistById(Long wishlistId) {
        return wishlistRepository.findById(wishlistId);
    }

    // Save a new wishlist
    public Wishlist createWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    // Update a wishlist
    public Wishlist updateWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    // Delete a wishlist
    public void deleteWishlist(Long wishlistId) {
        wishlistRepository.deleteById(wishlistId);
    }
}
