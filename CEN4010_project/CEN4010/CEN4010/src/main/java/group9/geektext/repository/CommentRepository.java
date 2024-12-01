package group9.geektext.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import group9.geektext.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBook_Id(Long bookId);  // Find comments by book ID
    List<Comment> findByUser_Id(Long userId);  // Find comments by user ID
}
