package BooksAndRead.BooksAndRead.controllers;

import BooksAndRead.BooksAndRead.entities.Publishing;
import BooksAndRead.BooksAndRead.services.PublishingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/publishing")
public class PublishingController {

    @Autowired
    private PublishingService publishingService;

    @GetMapping
    public ResponseEntity findAll(){
        List<Publishing> publishings = publishingService.findAll();
        return ResponseEntity.ok().body(publishings);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable String id){
        Optional<Publishing> publishing = publishingService.findById(id);
        return ResponseEntity.ok().body(publishing);
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity findByName(@PathVariable String name){
        Publishing publishing = publishingService.findByName(name);
        return ResponseEntity.ok(publishing);
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody Publishing publishing){
        publishing = publishingService.insert(publishing);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(publishing.getId())
                .toUri();

        return ResponseEntity.created(uri).body(publishing);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateById(@PathVariable String id, @RequestBody Publishing publishing) throws Exception {
        publishing = publishingService.update(id, publishing);
        return ResponseEntity.ok().body(publishing);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteById(@PathVariable String id){
        publishingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{name}")
    public ResponseEntity deleteByName(@PathVariable String name){
        publishingService.deleteByName(name);
        return ResponseEntity.noContent().build();
    }
}
