package syksy24;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

// Assuming you have an entity named Book and a repository named BookRepository
import syksy24.domain.Book;
import syksy24.domain.BookRepository;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    // Test for creating and saving a new Book
    @Test
    @Transactional
    @Rollback
    public void testCreateBook() {
        Book book = new Book();
        book.setTitle("New Book");
        book.setAuthor("John Doe");
        book.setIsbn("1234567890");
        

        Book savedBook = bookRepository.save(book);

        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getId()).isGreaterThan(0);
    }

    
    @Test
    public void testFindBookById() {
        
        Optional<Book> optionalBook = bookRepository.findById(1L);
        assertThat(optionalBook).isPresent();
        optionalBook.ifPresent(book -> {
            assertThat(book.getTitle()).isEqualTo("Ernest Hemingway");
        });
    }

    
    @Test
    public void testFindAllBooks() {
        List<Book> books = (List<Book>) bookRepository.findAll();
        assertThat(books).isNotEmpty();
    }

    
    @Test
    @Transactional
    @Rollback
    public void testUpdateBook() {
        
        Optional<Book> optionalBook = bookRepository.findById(1L);
        assertThat(optionalBook).isPresent();

        
    }

    
    @Test
    @Transactional
    @Rollback
    public void testDeleteBook() {
        Long bookId = 1L;
        boolean existsBeforeDelete = bookRepository.findById(bookId).isPresent();
        assertThat(existsBeforeDelete).isTrue();

        bookRepository.deleteById(bookId);

        boolean existsAfterDelete = bookRepository.findById(bookId).isPresent();
        assertThat(existsAfterDelete).isFalse();
    }
}
