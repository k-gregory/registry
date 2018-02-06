package io.github.k_gregory.registry.model;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    private Integer id;
    private String value;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
