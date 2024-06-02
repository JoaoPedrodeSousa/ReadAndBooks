package BooksAndRead.BooksAndRead.entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String resume;

    private List<Book> bookList;

    public Author() {
    }




}
