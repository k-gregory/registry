package io.github.k_gregory.registry.dto;

import io.github.k_gregory.registry.model.EnforcementState;

import java.util.Date;

public class TopEnforcementDTO {
    public final Long id;
    public final String sender;
    public final String receiver;
    public final String facilityName;
    public final Date startedAt;
    public final EnforcementState state;

    public TopEnforcementDTO(Long id, String sender, String receiver, String facilityName, Date startedAt, EnforcementState state) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.facilityName = facilityName;
        this.startedAt = startedAt;
        this.state = state;
    }
}
