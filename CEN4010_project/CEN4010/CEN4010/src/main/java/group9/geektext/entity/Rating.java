package group9.geektext.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating_value", nullable = false)
    private int ratingValue;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

 
    
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;


    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    // Constructors, getters, and setters

    public Rating() {
        this.timestamp = LocalDateTime.now(); // Set timestamp on creation
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
