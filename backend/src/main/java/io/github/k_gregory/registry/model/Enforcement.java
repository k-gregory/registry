package io.github.k_gregory.registry.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "enforcement")
public class Enforcement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name= "started_at", nullable = false)
    private Date startedAt;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnforcementState state;

    @OneToOne
    @JoinColumn(name = "sender_id")
    private Subject sender;

    @OneToOne
    @JoinColumn(name = "receiver_id")
    private Subject receiver;

    @ManyToOne
    private Facility facility;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Subject getSender() {
        return sender;
    }

    public void setSender(Subject sender) {
        this.sender = sender;
    }

    public Subject getReceiver() {
        return receiver;
    }

    public void setReceiver(Subject receiver) {
        this.receiver = receiver;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public EnforcementState getState() {
        return state;
    }

    public void setState(EnforcementState state) {
        this.state = state;
    }
}
