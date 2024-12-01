package group9.geektext.service;

import group9.geektext.entity.Book;
import group9.geektext.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        // Simply returning a list of books from the repository
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        // Fetch a book by its ID
        return bookRepository.findById(id).orElse(null);
    }

    public Book createBook(Book book) {
        // Save a new book
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        // Update an existing book by ID
        return bookRepository.findById(id)
                .map(book -> {
                    book.setIsbn(updatedBook.getIsbn());
                    book.setTitle(updatedBook.getTitle());
                    book.setGenre(updatedBook.getGenre());
                    book.setPrice(updatedBook.getPrice());
                    book.setAuthor(updatedBook.getAuthor()); // Set the new author if needed
                    return bookRepository.save(book);
                })
                .orElse(null);
    }

    public void deleteBook(Long id) {
        // Delete a book by ID
        bookRepository.deleteById(id);
    }
}
