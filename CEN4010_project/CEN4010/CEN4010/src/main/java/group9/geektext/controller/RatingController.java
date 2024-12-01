package group9.geektext.controller;

import group9.geektext.entity.Rating;
import group9.geektext.service.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    // Get all ratings for a book
    @GetMapping("/book/{bookId}")
    public List<Rating> getRatingsByBook(@PathVariable Long bookId) {
        return ratingService.getRatingsByBook(bookId);
    }

    // Get all ratings by a user
    @GetMapping("/user/{userId}")
    public List<Rating> getRatingsByUser(@PathVariable Long userId) {
        return ratingService.getRatingsByUser(userId);
    }

    // Add a new rating, with bookId as a parameter
    @PostMapping
    public ResponseEntity<Void> createRating(@RequestBody Rating rating) {
        ratingService.createRating(rating);
        return ResponseEntity.ok().build();
    }

    // Retrieve the average rating for a book
    @GetMapping("/book/{bookId}/average")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long bookId) {
        double averageRating = ratingService.getAverageRatingForBook(bookId);
        return ResponseEntity.ok(averageRating);
    }

    // Update a rating
    @PutMapping("/{ratingId}")
    public ResponseEntity<Rating> updateRating(@PathVariable Long ratingId, @RequestBody Rating updatedRating) {
        updatedRating.setId(ratingId);
        return ResponseEntity.ok(ratingService.updateRating(updatedRating));
    }

    // Delete a rating
    @DeleteMapping("/{ratingId}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long ratingId) {
        ratingService.deleteRating(ratingId);
        return ResponseEntity.noContent().build();
    }
}
