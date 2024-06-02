package BooksAndRead.BooksAndRead.services;

import BooksAndRead.BooksAndRead.entities.Publishing;
import BooksAndRead.BooksAndRead.repositories.PublishingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublishingService {

    @Autowired
    private PublishingRepository publishingRepository;

    public List<Publishing> findAll(){
        return publishingRepository.findAll();
    }

    public Optional<Publishing> findById(String id){
        return publishingRepository.findById(id);
    }

    public Optional<Publishing> findByName(String name){
        return Optional.ofNullable(publishingRepository.findByName(name));
    }

    public Publishing update(String id, Publishing publishing) throws Exception {
        try {
            Publishing oldPublishing = publishingRepository.getReferenceById(id);
            Publishing newPublishing = updateData(oldPublishing, publishing);
            return newPublishing;

        } catch (EntityNotFoundException e){
            throw new Exception(e.getMessage());
        }
    }

    private Publishing updateData(Publishing publishing, Publishing newDataPublishing){
        publishing.setName(newDataPublishing.getName());
        publishing.setResume(newDataPublishing.getResume());

        return publishing;
    }

    public void deleteById(String id){
        publishingRepository.deleteById(id);
    }

    public void deleteByName(String name){
        publishingRepository.deleteById(name);
    }
}
