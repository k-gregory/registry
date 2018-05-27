package io.github.k_gregory.registry.dto;

import java.util.Optional;

public class FacilityResponse {
    private int id;
    private String name;
    private Optional<Integer> headId;
    private String headName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<Integer> getHeadId() {
        return headId;
    }

    public void setHeadId(Optional<Integer> headId) {
        this.headId = headId;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }
}
