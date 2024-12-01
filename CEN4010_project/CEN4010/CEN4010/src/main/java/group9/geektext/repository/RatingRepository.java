package group9.geektext.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import group9.geektext.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByBook_Id(Long bookId);
    List<Rating> findByUser_Id(Long userId);
}
