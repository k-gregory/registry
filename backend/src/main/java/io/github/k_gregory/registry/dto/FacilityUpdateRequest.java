package io.github.k_gregory.registry.dto;

import java.util.Optional;

public class FacilityUpdateRequest {
    private String name;
    private Optional<Long> facilityHeadId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<Long> getFacilityHeadId() {
        return facilityHeadId;
    }

    public void setFacilityHeadId(Optional<Long> facilityHeadId) {
        this.facilityHeadId = facilityHeadId;
    }
}
