package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private int credit;

    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL)
    private Assessment assessment;

    @ManyToMany
    @JoinTable(
            name = "course_author",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    Set<Author> authors;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rating> ratings;

    public String toString() {
        return "Course [id=" + id + ", name=" + name + ", description=" + description + ", credit=" + credit + "]";
    }
}
