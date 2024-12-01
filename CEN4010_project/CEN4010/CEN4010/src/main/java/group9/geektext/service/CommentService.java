package group9.geektext.service;

import group9.geektext.entity.Book;
import group9.geektext.entity.Comment;
import group9.geektext.repository.BookRepository;
import group9.geektext.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    public CommentService(CommentRepository commentRepository, BookRepository bookRepository) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
    }

    // Get all comments for a specific book
    public List<Comment> getCommentsByBook(Long bookId) {
        return commentRepository.findByBook_Id(bookId);
    }

    // Get all comments by a specific user
    public List<Comment> getCommentsByUser(Long userId) {
        return commentRepository.findByUser_Id(userId);
    }

    // Save a new comment
    public Comment createComment(Comment comment) {
        // Find the book by bookId
        Book book = bookRepository.findById(comment.getBook().getId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        comment.setBook(book);
        return commentRepository.save(comment);
    }

    // Update an existing comment
    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // Delete a comment
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
