package BooksAndRead.BooksAndRead.services;

import BooksAndRead.BooksAndRead.entities.Author;
import BooksAndRead.BooksAndRead.repositories.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author insert(Author author){
        author.setName(author.getName().toLowerCase().replace(" ", "-"));
        author.setCountry(author.getCountry().toLowerCase().replace(" ", "-"));

        return authorRepository.save(author);
    }

    public List<Author> insertAll(List<Author> authors){
        return authorRepository.saveAll(authors);
    }

    public List<Author> findAll() {
        List<Author> authors = authorRepository.findAll();

        authors.forEach(author -> {
            author.setName(author
                    .getName()
                    .toLowerCase()
                    .replace(" ", "-"));

            author.setCountry(author
                    .getCountry()
                    .toLowerCase()
                    .replace(" ", "-"));
        });

        return authors;
    }


    public List<Author> findByCountry(String country){
        return authorRepository.findByCountry(country.toLowerCase().replace(" ", "-"));
    }

    public Optional<Author> findById(String id){
        return authorRepository.findById(id);
    }

    public Author findByName(String name){
        return authorRepository.findByName(name.toLowerCase().replace(" ", "-"));
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
        author.setName(newDataAuthor.getName().toLowerCase().replace(" ", "-"));
        author.setResume(newDataAuthor.getResume().toLowerCase().replace(" ", "-"));

        return author;
    }

    public void deleteById(String id){
        authorRepository.deleteById(id);
    }

    public void deleteByName(String name){
        authorRepository.deleteByName(name.toLowerCase().replace(" ", "-"));
    }
}
