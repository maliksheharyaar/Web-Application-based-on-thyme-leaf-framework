package ca.sheridan.assignment5.data;

import ca.sheridan.assignment5.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
