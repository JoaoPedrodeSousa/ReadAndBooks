package BooksAndRead.BooksAndRead.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_publishings")
public class Publishing {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String resume;

    @JsonIgnore
    @OneToMany(mappedBy = "publishing")
    private List<Book> bookList = new ArrayList<>();

    public Publishing() {
    }

    public Publishing(String id, String name, String resume) {
        this.id = id;
        this.name = name;
        this.resume = resume;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publishing that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Publishing{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", resume='" + resume + '\'' +
                ", bookList=" + bookList +
                '}';
    }
}
