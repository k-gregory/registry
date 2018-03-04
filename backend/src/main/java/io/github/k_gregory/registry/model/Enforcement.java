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

    @Column(name = "sender", nullable = false)
    private String sender;

    @Column(name = "receiver", nullable = false)
    private String receiver;

    @Column(name= "started_at", nullable = false)
    private Date startedAt;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnforcementState state;

    @ManyToOne
    private Facility facility;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
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
