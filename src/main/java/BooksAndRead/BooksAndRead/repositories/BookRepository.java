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

    List<Book> findByGenre(String genre);

    @Query("select b from Book b where b.publishing.id = :publishingId")
    List<Book> findByPublishing(@Param("publishingId") int publishingId);

    List<Book> findByLanguage(String language);

    Book findByIsbn(String isbn);

    List<Book> findByPublishingDateBetween(Date startDate, Date endDate);

    void deleteByName(String name);
}
