package BooksAndRead.BooksAndRead.services;

import BooksAndRead.BooksAndRead.entities.Book;
import BooksAndRead.BooksAndRead.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id){
        return bookRepository.findById(id);
    }

    public Book findByTitle(String title){
        return bookRepository.findByTitle(title);
    }

    public List<Book> findByGenre(String genre){
        return bookRepository.findByGenre(genre);
    }

    public List<Book> findByPublishing(int id){
        return bookRepository.findByPublishing(id);
    }

    public List<Book> findByLanguage(String language){
        return bookRepository.findByLanguage(language);
    }

    public Book findByIsbn(String isbn){
        return bookRepository.findByIsbn(isbn);
    }

    public List<Book> findByPublishingDateBetween(Date startDate, Date endDate){
        return bookRepository.findByPublishingDateBetween( startDate, endDate);
    }

    public void deleteById(Long id){
        bookRepository.deleteById(id);
    }

    public void deleteByName(String name){
        bookRepository.deleteByName(name);
    }

    public Book update(Long id, Book newBook){
        Book oldBook = bookRepository.getReferenceById(id);

        Book book = updateData(oldBook,newBook);
        return book;
    }

    private Book updateData(Book book, Book newDataBook){
        book.setAuthor(newDataBook.getAuthor());
        book.setTitle(newDataBook.getTitle());
        book.setPublishing(newDataBook.getPublishing());
        book.setDescription(newDataBook.getDescription());
        book.setGenre(newDataBook.getGenre());
        book.setLanguage(newDataBook.getLanguage());
        book.setIsbn(newDataBook.getIsbn());
        book.setPublishingDate(newDataBook.getPublishingDate());

        return book;
    }
}
