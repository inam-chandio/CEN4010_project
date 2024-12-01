package group9.geektext.controller;

import group9.geektext.entity.Comment;
import group9.geektext.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Get all comments for a book
    @GetMapping("/book/{bookId}")
    public List<Comment> getCommentsByBook(@PathVariable Long bookId) {
        return commentService.getCommentsByBook(bookId);
    }

    // Get all comments by a user
    @GetMapping("/user/{userId}")
    public List<Comment> getCommentsByUser(@PathVariable Long userId) {
        return commentService.getCommentsByUser(userId);
    }

    // Add a new comment
    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody Comment comment) {
        commentService.createComment(comment);
        return ResponseEntity.ok().build();
    }

    // Update a comment
    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long commentId, @RequestBody Comment updatedComment) {
        updatedComment.setId(commentId);
        return ResponseEntity.ok(commentService.updateComment(updatedComment));
    }

    // Delete a comment
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
