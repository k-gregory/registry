package io.github.k_gregory.registry.model;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id @Column(name = "id")
    public Integer id;

    @Column(name = "value")
    public String value;
}
