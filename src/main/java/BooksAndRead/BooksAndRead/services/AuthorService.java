package BooksAndRead.BooksAndRead.services;

import BooksAndRead.BooksAndRead.entities.Author;
import BooksAndRead.BooksAndRead.entities.Publishing;
import BooksAndRead.BooksAndRead.repositories.AuthorRepository;
import BooksAndRead.BooksAndRead.repositories.PublishingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    public Optional<Author> findById(String id){
        return authorRepository.findById(id);
    }

    public Optional<Author> findByName(String name){
        return Optional.ofNullable(authorRepository.findByName(name));
    }

    public Author update(String id, Author author) throws Exception {
        try {
            Author oldAuthor = authorRepository.getReferenceById(id);
            Author newAuthor  = updateData(oldAuthor, author);
            return newAuthor;

        } catch (EntityNotFoundException e){
            throw new Exception(e.getMessage());
        }
    }

    private Author updateData(Author author, Author newDataAuthor){
        author.setName(newDataAuthor.getName());
        author.setResume(newDataAuthor.getResume());

        return author;
    }

    public void deleteById(String id){
        authorRepository.deleteById(id);
    }

    public void deleteByName(String name){
        authorRepository.deleteById(name);
    }
}
