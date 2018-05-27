package io.github.k_gregory.registry.dto;

public class FacilityUpdateRequest {
    private String name;

    private Integer facilityHeadId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFacilityHeadId() {
        return facilityHeadId;
    }

    public void setFacilityHeadId(Integer facilityHeadId) {
        this.facilityHeadId = facilityHeadId;
    }
}
