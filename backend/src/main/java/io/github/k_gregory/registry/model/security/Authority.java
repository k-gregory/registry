package io.github.k_gregory.registry.model.security;

import javax.persistence.*;

@Entity
@Table(name = "authority")
public class Authority {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name", unique = true)
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
