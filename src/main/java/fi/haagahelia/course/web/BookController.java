package fi.haagahelia.course.web;

import java.util.concurrent.atomic.AtomicLong;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.BookRepository;


@Controller
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final AtomicLong counter = new AtomicLong();
    
    
    

    @Autowired
    private BookRepository bookRepository;

    

    // Method to show the list of books
    @RequestMapping(value="/booklist", method = RequestMethod.GET)
    public String showBookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }

    

    // Method to get a book by ID
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Book not found with id " + id));
    }

    

    // Method to show the form for adding a new book
    @GetMapping("/addbook")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());  // Pass an empty Book object
        return "addbook";
    }

    // Method to handle the form submission to add a new book
    @PostMapping("/addbook")
    public String addBook(Book book) {
        bookRepository.save(book);  // Save the new book to the database
        return "redirect:/booklist";  // Redirect back to the book list after saving
    }

    @RequestMapping("/deletebook/{id}")
    public String deleteBook(@PathVariable("id") Long bookId) {
        bookRepository.deleteById(bookId);  // Delete the book with the given ID
        return "redirect:/booklist";  // Redirect back to the book list after deletion
    }
}
