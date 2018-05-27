package io.github.k_gregory.registry.model;

import javax.persistence.*;

@Entity
@Table(name = "facility")
public class Facility {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "head_id")
    private Executant head;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Executant getHead() {
        return head;
    }

    public void setHead(Executant head) {
        this.head = head;
    }
}
