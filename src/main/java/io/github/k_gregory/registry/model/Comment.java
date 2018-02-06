package io.github.k_gregory.registry.model;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    private Long id;
    private String message;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "message", nullable = false)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
