package group9.geektext.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "shoppingcart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;  // The book in the cart

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // The user who owns the cart

    @Column(nullable = false)
    private int quantity = 1;  // Default quantity

    @Column(name = "added_at", nullable = false, updatable = false)
    private LocalDateTime addedAt = LocalDateTime.now();

    // Constructors
    public ShoppingCart() {}

    public ShoppingCart(Book book, User user, int quantity) {
        this.book = book;
        this.user = user;
        this.quantity = quantity;
        this.addedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", book=" + book.getTitle() +
                ", user=" + user.getUsername() +
                ", quantity=" + quantity +
                ", addedAt=" + addedAt +
                '}';
    }
}
