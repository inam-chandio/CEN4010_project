package group9.geektext.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import group9.geektext.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
