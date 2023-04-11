package edu.utcn.stackoverflow.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tags")
@Data //getters and setters
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Tag extends BaseEntity {
    private String name;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tags")
    private Collection<Question> questions;
}
