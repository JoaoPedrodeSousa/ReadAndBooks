package BooksAndRead.BooksAndRead.repositories;

import BooksAndRead.BooksAndRead.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitle(String title);

    @Query("select b from Book b where b.publisher.publisherName = :publisherName and b.title like %:title%")
    Book findByPublisherNameAndTitle(@Param("publisherName") String publisherName, @Param("title") String title);

    List<Book> findByGenre(String genre);
    @Query("select b from Book b where b.publisher.publisherName = :publisherName and b.genre like %:genre%")
    List<Book> findByPublisherNameAndGenre(@Param("publisherName") String publisherName, @Param("genre") String genre);

    @Query("select b from Book b where b.publisher.id = :publisherId")
    List<Book> findByPublisher(@Param("publisherId") String publisherId);

    List<Book> findByLanguage(String language);

    @Query("select b from Book b where b.publisher.publisherName = :publisherName and b.language like %:language%")
    List<Book> findByPublisherNameAndLanguage(@Param("publisherName") String publisherName, @Param("language") String language);

    Book findByIsbn(String isbn);

    List<Book> findByPublishingDateBetween(Date startDate, Date endDate);

    void deleteByTitle(String title);
}
