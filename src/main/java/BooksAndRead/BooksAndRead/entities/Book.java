package BooksAndRead.BooksAndRead.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tb_books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Author author;
    @Column(nullable = false)
    private String genre;
    @Column(unique = true,nullable = false)
    private Publishing publishing;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Date publishingDate;
    @Column(nullable = false)
    private String language;

    public Book() {
    }

    public Book(Long id, String title, Author author, String genre, Publishing publishing, String description, Date publishingDate, String language) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publishing = publishing;
        this.description = description;
        this.publishingDate = publishingDate;
        this.language = language;
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

    public Publishing getPublishing() {
        return publishing;
    }

    public void setPublishing(Publishing publishing) {
        this.publishing = publishing;
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
                ", name='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", publishing='" + publishing + '\'' +
                ", description='" + description + '\'' +
                ", publishingDate=" + publishingDate +
                ", language='" + language + '\'' +
                '}';
    }
}
