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

    @Query("select b from Book b where b.publishing.name = :publishingName and b.title like %:title%")
    Book findByPublishingNameAndTitle(@Param("publishingName") String publishingName, @Param("title") String title);

    List<Book> findByGenre(String genre);
    @Query("select b from Book b where b.publishing.name = :publishingName and b.genre like %:genre%")
    List<Book> findByPublishingNameAndGenre(@Param("publishingName") String publishingName, @Param("genre") String genre);

    @Query("select b from Book b where b.publishing.id = :publishingId")
    List<Book> findByPublishing(@Param("publishingId") String publishingId);

    List<Book> findByLanguage(String language);

    @Query("select b from Book b where b.publishing.name = :publishingName and b.language like %:language%")
    List<Book> findByPublishingNameAndLanguage(@Param("publishingName") String publishingName, @Param("language") String language);

    Book findByIsbn(String isbn);

    List<Book> findByPublishingDateBetween(Date startDate, Date endDate);

    void deleteByTitle(String title);
}
