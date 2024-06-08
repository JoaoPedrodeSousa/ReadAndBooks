package BooksAndRead.BooksAndRead.controllers;

import BooksAndRead.BooksAndRead.entities.Author;
import BooksAndRead.BooksAndRead.entities.Book;
import BooksAndRead.BooksAndRead.entities.BookRequestDTO;
import BooksAndRead.BooksAndRead.entities.Publishing;
import BooksAndRead.BooksAndRead.services.AuthorService;
import BooksAndRead.BooksAndRead.services.BookService;
import BooksAndRead.BooksAndRead.services.PublishingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/{publishingName}")
public class PublishingBookController {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Autowired
    private PublishingService publishingService;

    @PostMapping
    public ResponseEntity insertBook(@PathVariable String publishingName, @RequestBody BookRequestDTO bookRequestDTO){
        Publishing publishing = publishingService.findByName(publishingName);
        Author author = authorService.findByName(bookRequestDTO.authorName());

        Book book = createNewBook(bookRequestDTO, author, publishing);

        book = bookService.insert(book);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(book.getId())
                .toUri();


        return ResponseEntity.created(uri).body(book);
    }

    private Book createNewBook(BookRequestDTO bookRequestDTO, Author author, Publishing publishing){
        return new Book(
                null,
                bookRequestDTO.title(),
                author,
                bookRequestDTO.genre(),
                publishing,
                bookRequestDTO.description(),
                bookRequestDTO.publishingDate(),
                bookRequestDTO.language(),
                bookRequestDTO.isbn()
        );
    }

//    @PostMapping(value = "/{publishingName}/authors/")
//    public ResponseEntity insertAuthor(@PathVariable String publishingName, @RequestBody Author author){
//
//        author = authorService.insert(author);
//        URI uri = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(author.getId())
//                .toUri();
//
//        return ResponseEntity.created(uri).body(author);
//    }

    @GetMapping(value = "/books")
    public ResponseEntity findAll(@PathVariable String publishingName){
        List<Book> books = bookService.findByPublishing(publishingName);
        return ResponseEntity.ok().body(books);
    }

    @GetMapping(value = "/books/{id}")
    public ResponseEntity findById(@PathVariable String publishingName, @PathVariable Long id){
        Optional<Book> book = bookService.findById(id);
        return ResponseEntity.ok().body(book);
    }

    @GetMapping(value = "/books")
    public ResponseEntity findByTitle(@PathVariable String publishingName, @RequestParam String title){
        Book book = bookService.findByTitle(title);
        return ResponseEntity.ok().body(book);
    }

    @GetMapping(value = "/books")
    public ResponseEntity findByGenre(@PathVariable String publishingName, @RequestParam String genre){
        List<Book> book = bookService.findByGenre(genre);
        return ResponseEntity.ok().body(book);
    }

    @GetMapping(value = "/books")
    public ResponseEntity findByLanguage(@PathVariable String publishingName, @RequestParam String language){
        List<Book> books = bookService.findByLanguage(language);
        return ResponseEntity.ok().body(books);
    }

    @GetMapping(value = "/books")
    public ResponseEntity findByIsbn(@PathVariable String publishingName, @RequestParam String isbn){
        Book book = bookService.findByIsbn(isbn);
        return ResponseEntity.ok().body(book);
    }



//    @GetMapping(value = "/{id}")
//    public ResponseEntity findByPublishingDateBetween(@RequestParam Date startDate, @RequestParam Date endDate){
//        List<Book> books = bookService.findByPublishingDateBetween(startDate, endDate);
//        return ResponseEntity.ok().body(books);
//    }



//    @GetMapping(value = "/{publishingName}/authors/{id}")
//    public ResponseEntity findBooksByAuthor(@PathVariable String publishingName, @RequestBody Author author) {
//
//    }

}
