package group9.geektext.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import group9.geektext.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findByUser_Id(Long userId);  // Find wishlists by user ID
}
