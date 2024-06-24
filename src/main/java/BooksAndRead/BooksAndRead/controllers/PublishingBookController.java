package BooksAndRead.BooksAndRead.controllers;

import BooksAndRead.BooksAndRead.entities.Author;
import BooksAndRead.BooksAndRead.entities.Book;
import BooksAndRead.BooksAndRead.entities.BookRequestDTO;
import BooksAndRead.BooksAndRead.entities.Publisher;
import BooksAndRead.BooksAndRead.services.AuthorService;
import BooksAndRead.BooksAndRead.services.BookService;
import BooksAndRead.BooksAndRead.services.PublishingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/a/{publishingName}")
public class PublishingBookController {

    private final AuthorService authorService;
    private final BookService bookService;
    private final PublishingService publishingService;

    @Autowired
    public PublishingBookController(AuthorService authorService,
                                    BookService bookService,
                                    PublishingService publishingService){
        this.authorService = authorService;
        this.bookService = bookService;
        this.publishingService = publishingService;
    }
    @PostMapping
    public ResponseEntity insertBook(@PathVariable String publishingName, @RequestBody BookRequestDTO bookRequestDTO){

        Publisher publishing = publishingService.findByName(publishingName);
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
    public ResponseEntity findByParams(@PathVariable String publishingName, @RequestParam Map<String, String> params){
        List<Book> books = bookService.findByParams(publishingName, params);

        System.out.println(params.toString());
        return ResponseEntity.ok().body(books);
    }


}
