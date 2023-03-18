package edu.utcn.stackoverflow.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data //getters and setters
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "questions")
public class Question extends BaseEntity {
    private String title;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;
    private String content;
    private Timestamp date;
    private String picture;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "question_tag",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Collection<Tag> tags;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "question")
    private Collection<Answer> answers;
}
