package group9.geektext.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import group9.geektext.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
