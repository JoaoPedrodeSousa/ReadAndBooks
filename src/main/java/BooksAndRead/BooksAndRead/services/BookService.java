package BooksAndRead.BooksAndRead.services;

import BooksAndRead.BooksAndRead.entities.Book;
import BooksAndRead.BooksAndRead.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book insert(Book book){
        book.setTitle(book.getTitle().toLowerCase().replace(" ", "-"));
        book.setLanguage(book.getLanguage().toLowerCase().replace(" ", "-"));
        book.setGenre(book.getGenre().toLowerCase().replace(" ", "-"));


        return bookRepository.save(book);
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id){
        return bookRepository.findById(id);
    }

    public Book findByTitle(String title){
        return bookRepository.findByTitle(title.toLowerCase().replace(" ", "-"));
    }

    public List<Book> findByGenre(String genre){
        return bookRepository.findByGenre(genre);
    }

    public List<Book> findByPublisher(String id){
        return bookRepository.findByPublisher(id);
    }

    public List<Book> findByLanguage(String language){
        return bookRepository.findByLanguage(language.toLowerCase().replace(" ", "-"));
    }

    public Book findByIsbn(String isbn){
        return bookRepository.findByIsbn(isbn);
    }

    public List<Book> findByPublishingDateBetween(Date startDate, Date endDate){
        return bookRepository.findByPublishingDateBetween(startDate, endDate);
    }

    public List<Book> findByParams(Map<String, String> params){
        List<Book> books = new ArrayList<>();

        if (params.containsKey("title")){
            String title = params.get("title");
            Book book = this.findByTitle(title);
            books.add(book);
        }
        if(params.containsKey("genre")){
            String genre = params.get("genre");
            List<Book> booksGenre = this.findByGenre(genre);

            for(Book book : booksGenre){
                books.add(book);
            }
        }
        if(params.containsKey("language")){
            String language = params.get("language");
            List<Book> booksLanguage = this.findByLanguage(language);
            for(Book book : booksLanguage){
                books.add(book);
            }
        }
        if (params.containsKey("isbn")){
            String isbn = params.get("isbn");
            Book book = this.findByIsbn(isbn);
            books.add(book);
        }
        if(params.containsKey("publishing")){
            String publishing = params.get("publishing");
            List<Book> booksPublishing = this.findByPublisher(publishing);

            for(Book book : booksPublishing){
                books.add(book);
            }
        }

        return books;
    }

    public List<Book> findByParams(String publishingName, Map<String, String> params){
        List<Book> books = new ArrayList<>();

        if (params.containsKey("title")){
            String title = params.get("title");
            System.out.println(title);

            Book book = bookRepository.findByPublisherNameAndTitle(publishingName, title);
            books.add(book);
        }
        if(params.containsKey("genre")){
            String genre = params.get("genre");
            System.out.println(genre);

            List<Book> booksGenre = bookRepository.findByPublisherNameAndGenre(publishingName,genre);

            for(Book book : booksGenre){
                books.add(book);
            }
        }
        if(params.containsKey("language")){
            String language = params.get("language");
            List<Book> booksLanguage = bookRepository.findByPublisherNameAndLanguage(publishingName, language);
            System.out.println(language);

            for(Book book : booksLanguage){
                books.add(book);
            }
        }
        if (params.containsKey("isbn")){
            String isbn = params.get("isbn");
            Book book = this.findByIsbn(isbn);
            books.add(book);
            System.out.println(isbn);

        }

        return books;
    }


    public void deleteById(Long id){
        bookRepository.deleteById(id);
    }

    public void deleteByName(String title){
        bookRepository.deleteByTitle(title.toLowerCase().replace(" ", "-"));
    }

    public Book update(Long id, Book newBook){
        Book oldBook = bookRepository.getReferenceById(id);

        Book book = updateData(oldBook,newBook);
        return book;
    }

    private Book updateData(Book book, Book newDataBook){
        book.setAuthor(newDataBook.getAuthor());
        book.setTitle(newDataBook.getTitle().toLowerCase().replace(" ", "-"));
        book.setPublisher(newDataBook.getPublisher());
        book.setDescription(newDataBook.getDescription());
        book.setGenre(newDataBook.getGenre().toLowerCase().replace(" ", "-"));
        book.setLanguage(newDataBook.getLanguage().toLowerCase().replace(" ", "-"));
        book.setIsbn(newDataBook.getIsbn());
        book.setPublishingDate(newDataBook.getPublishingDate());

        return book;
    }
}
