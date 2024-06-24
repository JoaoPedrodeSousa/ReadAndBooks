package BooksAndRead.BooksAndRead.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_publisher")
public class Publisher implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private String publisherName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String resume;

    @OneToMany(mappedBy = "publisher")
    private List<Book> bookList = new ArrayList<>();

    private String authority;

    public Publisher() {
    }

    public Publisher(String publisherName, String password, String resume) {
        this.publisherName = publisherName;
        this.password = password;
        this.resume = resume;
        this.authority = "PUBLISHER";
    }

    public Publisher(String id, String publisherName, String password, String resume) {
        this.id = id;
        this.publisherName = publisherName;
        this.password = password;
        this.resume = resume;
        this.authority = "PUBLISHER";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getAuthority()));

    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return publisherName;
    }

    public void setUsername(String name) {
        this.publisherName = name;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public String getAuthority() {
        return authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publisher that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id='" + id + '\'' +
                ", publisherName='" + publisherName + '\'' +
                ", resume='" + resume + '\'' +
                ", bookList=" + bookList +
                '}';
    }
}
