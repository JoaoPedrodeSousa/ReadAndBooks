package BooksAndRead.BooksAndRead.repositories;

import BooksAndRead.BooksAndRead.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher,String> {
    Publisher findByPublisherName(String name);

}


