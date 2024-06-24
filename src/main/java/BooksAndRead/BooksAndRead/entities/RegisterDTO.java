package BooksAndRead.BooksAndRead.entities;

public record RegisterDTO(
    String authority,
    String username,
    String email,
    String password,
    String resume){
}
