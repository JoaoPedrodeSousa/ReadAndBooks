package BooksAndRead.BooksAndRead.entities;

import java.util.Date;

public record BookRequestDTO(String title, String authorName, String genre, String description, Date publishingDate, String language, String isbn) {
}
