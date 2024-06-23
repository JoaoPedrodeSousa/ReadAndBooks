package BooksAndRead.BooksAndRead.repositories;

import BooksAndRead.BooksAndRead.entities.Book;
import BooksAndRead.BooksAndRead.entities.Publishing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitle(String title);

    @Query("select b from Book b where b.publishing.id = :publishingId and b.title like %:title%")
    Book findByPublishingAndTitle(@Param("publishingId") String publishingId,@Param("title") String title);

    List<Book> findByGenre(String genre);
    @Query("select b from Book b where b.publishing.id = :publishingId and b.genre like %:genre%")
    List<Book> findByPublishingAndGenre(@Param("publishingId") String publishingId,@Param("genre") String genre);
    @Query("select b from Book b where b.publishing.id = :publishingId")
    List<Book> findByPublishing(@Param("publishingId") String publishingId);

    List<Book> findByLanguage(String language);

    @Query("select b from Book b where b.publishing.id = :publishingId and b.language like %:language%")
    List<Book> findByPublishingAndLanguage(@Param("publishingId") String publishingId,@Param("language") String language);

    Book findByIsbn(String isbn);

    List<Book> findByPublishingDateBetween(Date startDate, Date endDate);

    void deleteByTitle(String title);
}
