package edu.utcn.stackoverflow.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data //getters and setters
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    private String lastName;
    private String firstName;
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
    private Integer role;
    //0 for user, 1 for moderator

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "author")
    private Collection<Question> questions;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "author")
    private Collection<QuestionVote> questionVotes;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "author")
    private Collection<AnswerVote> answerVotes;
}
