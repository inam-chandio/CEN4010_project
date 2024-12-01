-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS `geek_text` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `geek_text`;

-- Table structure for `users`
CREATE TABLE `users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL UNIQUE,
  `password` VARCHAR(255) NOT NULL,
  `name` VARCHAR(100) DEFAULT NULL,
  `email` VARCHAR(100) DEFAULT NULL,
  `address` TEXT,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Table structure for `authors`
CREATE TABLE `authors` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `biography` TEXT,
  `publisher` VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Table structure for `books`
CREATE TABLE `books` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `isbn` VARCHAR(13) NOT NULL UNIQUE,
  `title` VARCHAR(255) NOT NULL,
  `genre` VARCHAR(100) NOT NULL,
  `description` TEXT,
  `publisher` VARCHAR(100),
  `price` DECIMAL(10,2) NOT NULL,
  `copies_sold` INT DEFAULT 0,
  `year_published` INT NOT NULL,
  `author_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_books_author` FOREIGN KEY (`author_id`) REFERENCES `authors`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Table structure for `ratings`
CREATE TABLE `ratings` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `book_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  `rating` INT CHECK (`rating` >= 1 AND `rating` <= 5),
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `timestamp` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_ratings_book` FOREIGN KEY (`book_id`) REFERENCES `books`(`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_ratings_user` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Table structure for `comments`
CREATE TABLE `comments` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `book_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  `comment` TEXT,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_comments_book` FOREIGN KEY (`book_id`) REFERENCES `books`(`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_comments_user` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Table structure for `shoppingcart`
CREATE TABLE `shoppingcart` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `book_id` BIGINT NOT NULL,
  `quantity` INT DEFAULT 1,
  `added_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_cart_user` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_cart_book` FOREIGN KEY (`book_id`) REFERENCES `books`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Table structure for `wishlists`
CREATE TABLE `wishlists` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `name` VARCHAR(100) DEFAULT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_wishlist_user` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Table structure for `wishlistbooks`
CREATE TABLE `wishlistbooks` (
  `wishlist_id` BIGINT NOT NULL,
  `book_id` BIGINT NOT NULL,
  PRIMARY KEY (`wishlist_id`, `book_id`),
  CONSTRAINT `fk_wishlistbooks_wishlist` FOREIGN KEY (`wishlist_id`) REFERENCES `wishlists`(`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_wishlistbooks_book` FOREIGN KEY (`book_id`) REFERENCES `books`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Table structure for `creditcards`
CREATE TABLE `creditcards` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `card_number` VARCHAR(20) NOT NULL,
  `expiration_date` DATE NOT NULL,
  `cvv` VARCHAR(4) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_creditcards_user` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- View structure for `topsellers`
CREATE VIEW `topsellers` AS
SELECT 
  `books`.`id` AS `id`, 
  `books`.`title` AS `title`, 
  `books`.`copies_sold` AS `copies_sold`
FROM `books`
ORDER BY `books`.`copies_sold` DESC
LIMIT 10;




-- Insert into `users`
INSERT INTO users (username, password, name, email, address)
VALUES 
('user1', 'password1', 'John Doe', 'john@example.com', '123 Main St'),
('user2', 'password2', 'Jane Smith', 'jane@example.com', '456 Oak St'),
('user3', 'password3', 'Alice Johnson', 'alice@example.com', '789 Pine St'),
('user4', 'password4', 'Bob Brown', 'bob@example.com', '101 Maple St'),
('user5', 'password5', 'Charlie Davis', 'charlie@example.com', '202 Birch St');

-- Insert into `authors`
INSERT INTO authors (biography, publisher)
VALUES
('Author 1 biography', 'Publisher 1'),
('Author 2 biography', 'Publisher 2'),
('Author 3 biography', 'Publisher 3'),
('Author 4 biography', 'Publisher 4'),
('Author 5 biography', 'Publisher 5');

-- Insert into `books`
INSERT INTO books (isbn, title, genre, description, publisher, price, copies_sold, year_published, author_id)
VALUES
('1111111111111', 'Book 1', 'Fiction', 'Book 1 description', 'Publisher 1', 19.99, 100, 2021, 1),
('2222222222222', 'Book 2', 'Non-fiction', 'Book 2 description', 'Publisher 2', 29.99, 150, 2022, 2),
('3333333333333', 'Book 3', 'Sci-Fi', 'Book 3 description', 'Publisher 3', 9.99, 50, 2023, 3),
('4444444444444', 'Book 4', 'Fantasy', 'Book 4 description', 'Publisher 4', 14.99, 200, 2020, 4),
('5555555555555', 'Book 5', 'Adventure', 'Book 5 description', 'Publisher 5', 24.99, 300, 2021, 5);

-- Insert into `ratings`
INSERT INTO ratings (book_id, user_id, rating)
VALUES
(1, 1, 5),
(2, 2, 4),
(3, 3, 3),
(4, 4, 5),
(5, 5, 2);

-- Insert into `comments`
INSERT INTO comments (book_id, user_id, comment)
VALUES
(1, 1, 'Great book, highly recommend!'),
(2, 2, 'Interesting read, learned a lot.'),
(3, 3, 'Not my favorite, but still good.'),
(4, 4, 'Amazing story, I couldn’t put it down.'),
(5, 5, 'Not what I expected, but decent.');

-- Insert into `shoppingcart`
INSERT INTO shoppingcart (user_id, book_id, quantity)
VALUES
(1, 1, 2),
(2, 2, 1),
(3, 3, 3),
(4, 4, 1),
(5, 5, 2);

-- Insert into `wishlists`
INSERT INTO wishlists (user_id, name)
VALUES
(1, 'John’s Wishlist'),
(2, 'Jane’s Wishlist'),
(3, 'Alice’s Wishlist'),
(4, 'Bob’s Wishlist'),
(5, 'Charlie’s Wishlist');

-- Insert into `wishlistbooks`
INSERT INTO wishlistbooks (wishlist_id, book_id)
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- Insert into `creditcards`
INSERT INTO creditcards (user_id, card_number, expiration_date, cvv)
VALUES
(1, '4111111111111111', '2025-12-31', '123'),
(2, '4111111111111112', '2026-11-30', '456'),
(3, '4111111111111113', '2024-10-31', '789'),
(4, '4111111111111114', '2025-09-30', '101'),
(5, '4111111111111115', '2023-08-31', '202');

