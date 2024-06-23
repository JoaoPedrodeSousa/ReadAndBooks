package BooksAndRead.BooksAndRead.controllers;

import BooksAndRead.BooksAndRead.entities.Author;
import BooksAndRead.BooksAndRead.entities.Book;
import BooksAndRead.BooksAndRead.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController(value = "/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/list")

    public ResponseEntity findAll(){
        List<Book> books = bookService.findAll();
        return ResponseEntity.ok().body(books);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        Optional<Book> book = bookService.findById(id);
        return ResponseEntity.ok().body(book);
    }

    public ResponseEntity findByParams(@RequestParam Map<String, String> params){
        List<Book> books = bookService.findByParams(params);
        return ResponseEntity.ok().body(books);
    }
//
////    @GetMapping(value = "/{id}")
////    public ResponseEntity findByPublishingDateBetween(@RequestParam Date startDate, @RequestParam Date endDate){
////        List<Book> books = bookService.findByPublishingDateBetween(startDate, endDate);
////        return ResponseEntity.ok().body(books);
////    }


}
