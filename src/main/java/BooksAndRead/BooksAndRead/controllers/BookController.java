package BooksAndRead.BooksAndRead.controllers;

import BooksAndRead.BooksAndRead.entities.Author;
import BooksAndRead.BooksAndRead.entities.Book;
import BooksAndRead.BooksAndRead.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController(value = "/books")
public class BookController {

    @Autowired
    private BookService bookService;

//    public ResponseEntity findAll(){
//        List<Book> books = bookService.findAll();
//        return ResponseEntity.ok().body(books);
//    }
//
//    @GetMapping(value = "/{id}")
//    public ResponseEntity findById(@PathVariable Long id){
//        Optional<Book> book = bookService.findById(id);
//        return ResponseEntity.ok().body(book);
//    }
//
//    @GetMapping(value = "/{title}")
//    public ResponseEntity findByTitle(@PathVariable String title){
//        Book book = bookService.findByTitle(title);
//        return ResponseEntity.ok().body(book);
//    }
//
//    @GetMapping(value = "/{genre}")
//    public ResponseEntity findByGenre(@PathVariable String genre){
//        List<Book> book = bookService.findByGenre(genre);
//        return ResponseEntity.ok().body(book);
//    }
//
//    @GetMapping(value = "/{publishing}")
//    public ResponseEntity findByPublishing(@PathVariable int id){
//        List<Book> books = bookService.findByPublishing(id);
//        return ResponseEntity.ok().body(books);
//    }
//
//    @GetMapping(value = "/{language}")
//    public ResponseEntity findByLanguage(@PathVariable String language){
//        List<Book> books = bookService.findByLanguage(language);
//        return ResponseEntity.ok().body(books);
//    }
//
//    @GetMapping(value = "/{isbn}")
//    public ResponseEntity findByIsbn(@PathVariable String isbn){
//        Book book = bookService.findByIsbn(isbn);
//        return ResponseEntity.ok().body(book);
//    }
//
////    @GetMapping(value = "/{id}")
////    public ResponseEntity findByPublishingDateBetween(@RequestParam Date startDate, @RequestParam Date endDate){
////        List<Book> books = bookService.findByPublishingDateBetween(startDate, endDate);
////        return ResponseEntity.ok().body(books);
////    }


}
