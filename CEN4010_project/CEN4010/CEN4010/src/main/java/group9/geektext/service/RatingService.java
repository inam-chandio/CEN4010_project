package group9.geektext.service;

import group9.geektext.entity.Book;
import group9.geektext.entity.Rating;
import group9.geektext.repository.BookRepository;
import group9.geektext.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final BookRepository bookRepository;

    public RatingService(RatingRepository ratingRepository, BookRepository bookRepository) {
        this.ratingRepository = ratingRepository;
        this.bookRepository = bookRepository;
    }

    // Get all ratings for a book
    public List<Rating> getRatingsByBook(Long bookId) {
        return ratingRepository.findByBook_Id(bookId);
    }

    // Get all ratings by a user
    public List<Rating> getRatingsByUser(Long userId) {
        return ratingRepository.findByUser_Id(userId);
    }

    // Create a new rating
    public Rating createRating(Rating rating) {
        // Validate book existence
        Book book = bookRepository.findById(rating.getBook().getId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        rating.setBook(book);
        return ratingRepository.save(rating);
    }

    // Calculate the average rating for a book
    public double getAverageRatingForBook(Long bookId) {
        List<Rating> ratings = ratingRepository.findByBook_Id(bookId);
        return ratings.stream()
                .mapToInt(Rating::getRatingValue)
                .average()
                .orElse(0.0);
    }

    // Update a rating
    public Rating updateRating(Rating updatedRating) {
        return ratingRepository.save(updatedRating);
    }

    // Delete a rating
    public void deleteRating(Long ratingId) {
        ratingRepository.deleteById(ratingId);
    }
}
