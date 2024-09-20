package fi.haagahelia.course.web;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.BookRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // Method to show the list of books
    @GetMapping("/booklist")
    public String showBookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
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
