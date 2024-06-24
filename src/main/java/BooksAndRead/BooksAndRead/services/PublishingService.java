package BooksAndRead.BooksAndRead.services;

import BooksAndRead.BooksAndRead.entities.Publisher;
import BooksAndRead.BooksAndRead.repositories.PublisherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublishingService {

    @Autowired
    private PublisherRepository publisherRepository;

    public Publisher insert(Publisher publishing){
        return publisherRepository.save(publishing);
    }

    public List<Publisher> findAll(){
        return publisherRepository.findAll();
    }

    public Optional<Publisher> findById(String id){
        return publisherRepository.findById(id);
    }

    public Publisher findByName(String name){
        return publisherRepository.findByPublisherName(name);
    }

    public Publisher update(String id, Publisher publishing) throws Exception {
        try {
            Publisher oldPublishing = publisherRepository.getReferenceById(id);
            Publisher newPublishing = updateData(oldPublishing, publishing);
            return newPublishing;

        } catch (EntityNotFoundException e){
            throw new Exception(e.getMessage());
        }
    }

    private Publisher updateData(Publisher publishing, Publisher newDataPublishing){
        publishing.setUsername(newDataPublishing.getUsername());
        publishing.setResume(newDataPublishing.getResume());

        return publishing;
    }

    public void deleteById(String id){
        publisherRepository.deleteById(id);
    }

    public void deleteByName(String name){
        publisherRepository.deleteById(name);
    }
}
