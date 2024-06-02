package BooksAndRead.BooksAndRead.repositories;

import BooksAndRead.BooksAndRead.entities.Publishing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublishingRepository extends JpaRepository<Publishing,String> {
    Publishing findByName(String name);

}


