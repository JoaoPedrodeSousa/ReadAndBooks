package BooksAndRead.BooksAndRead.controllers;

import BooksAndRead.BooksAndRead.entities.Author;
import BooksAndRead.BooksAndRead.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity findAll(){
        List<Author> authors = authorService.findAll();
        return ResponseEntity.ok().body(authors);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable String id){
        Optional<Author> author = authorService.findById(id);
        return ResponseEntity.ok().body(author);
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity findByName(@PathVariable String name){
        Author authors = authorService.findByName(name);
        return ResponseEntity.ok(authors);
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody Author author){
        author = authorService.insert(author);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(author.getId())
                .toUri();

        return ResponseEntity.created(uri).body(author);
    }

    @PostMapping(value = "/list")
    public ResponseEntity insertAll(@RequestBody List<Author> authors){
        authorService.insertAll(authors);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateById(@PathVariable String id, @RequestBody Author author) throws Exception {
        author = authorService.update(id, author);
        return ResponseEntity.ok().body(author);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteById(@PathVariable String id){
        authorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{name}")
    public ResponseEntity deleteByName(@PathVariable String name){
        authorService.deleteByName(name);
        return ResponseEntity.noContent().build();
    }
}
