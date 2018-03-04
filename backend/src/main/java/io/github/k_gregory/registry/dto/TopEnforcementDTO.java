package io.github.k_gregory.registry.dto;

import io.github.k_gregory.registry.model.EnforcementState;

import java.util.Date;

public class TopEnforcementDTO {
    private Long id;
    private String sender;
    private String receiver;
    private String facilityName;
    private Date startedAt;
    private EnforcementState state;

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

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
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
