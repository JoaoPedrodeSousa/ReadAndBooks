package BooksAndRead.BooksAndRead.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tb_books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id",
            nullable = false)
    private Author author;

    @Column(nullable = false)
    private String genre;

    @ManyToOne
    @JoinColumn(name = "publisher_id",
            nullable = false)
    private Publisher publisher;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Date publishingDate;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private String isbn;


    public Book() {
    }

    public Book(String title, Author author, String genre, Publisher publisher, String description, Date publishingDate, String language, String isbn) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.description = description;
        this.publishingDate = publishingDate;
        this.language = language;
        this.isbn = isbn;
    }

    public Book(Long id, String title, Author author, String genre, Publisher publisher, String description, Date publishingDate, String language, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.description = description;
        this.publishingDate = publishingDate;
        this.language = language;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(Date publishingDate) {
        this.publishingDate = publishingDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", publisher='" + publisher + '\'' +
                ", description='" + description + '\'' +
                ", publishingDate=" + publishingDate +
                ", language='" + language + '\'' +
                '}';
    }
}
