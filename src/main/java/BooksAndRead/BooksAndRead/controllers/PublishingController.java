package BooksAndRead.BooksAndRead.controllers;

import BooksAndRead.BooksAndRead.entities.Publisher;
import BooksAndRead.BooksAndRead.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/publisher")
public class PublishingController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public ResponseEntity findAll(){
        List<Publisher> publishings = publisherService.findAll();
        return ResponseEntity.ok().body(publishings);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable String id){
        Optional<Publisher> publishing = publisherService.findById(id);
        return ResponseEntity.ok().body(publishing);
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity findByName(@PathVariable String name){
        Publisher publishing = publisherService.findByName(name);
        return ResponseEntity.ok(publishing);
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody Publisher publishing){
        publishing = publisherService.insert(publishing);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(publishing.getId())
                .toUri();

        return ResponseEntity.created(uri).body(publishing);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateById(@PathVariable String id, @RequestBody Publisher publishing) throws Exception {
        publishing = publisherService.update(id, publishing);
        return ResponseEntity.ok().body(publishing);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteById(@PathVariable String id){
        publisherService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{name}")
    public ResponseEntity deleteByName(@PathVariable String name){
        publisherService.deleteByName(name);
        return ResponseEntity.noContent().build();
    }
}
