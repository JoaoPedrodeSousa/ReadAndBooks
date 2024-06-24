package BooksAndRead.BooksAndRead.controllers;

import BooksAndRead.BooksAndRead.entities.Author;
import BooksAndRead.BooksAndRead.entities.Book;
import BooksAndRead.BooksAndRead.entities.BookRequestDTO;
import BooksAndRead.BooksAndRead.entities.Publisher;
import BooksAndRead.BooksAndRead.services.AuthorService;
import BooksAndRead.BooksAndRead.services.BookService;
import BooksAndRead.BooksAndRead.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/{publisherName}")
public class PublisherBookController {

    private final AuthorService authorService;
    private final BookService bookService;
    private final PublisherService publisherService;

    @Autowired
    public PublisherBookController(AuthorService authorService,
                                   BookService bookService,
                                   PublisherService publisherService){
        this.authorService = authorService;
        this.bookService = bookService;
        this.publisherService = publisherService;
    }
    @PostMapping
    public ResponseEntity insertBook(@PathVariable String publisherName, @RequestBody BookRequestDTO bookRequestDTO){

        Publisher publishing = publisherService.findByName(publisherName);
        Author author = authorService.findByName(bookRequestDTO.authorName());

        Book book = createNewBook(bookRequestDTO, author, publishing);

        System.out.println(book.toString());

        book = bookService.insert(book);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(book.getId())
                .toUri();


        return ResponseEntity.created(uri).body(book);
    }
    private Book createNewBook(BookRequestDTO bookRequestDTO, Author author, Publisher publishing){
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
    @GetMapping
    public ResponseEntity findAll(@PathVariable String publisherName){
        List<Book> books = bookService.findByPublisher(publisherName);
        return ResponseEntity.ok().body(books);
    }
    @GetMapping(value = "/books/{id}")
    public ResponseEntity findById(@PathVariable String publisherName, @PathVariable Long id){
        Optional<Book> book = bookService.findById(id);
        return ResponseEntity.ok().body(book);
    }
    @GetMapping(value = "/books")
    public ResponseEntity findByParams(@PathVariable String publisherName, @RequestParam Map<String, String> params){
        List<Book> books = bookService.findByParams(publisherName, params);

        return ResponseEntity.ok().body(books);
    }


}
