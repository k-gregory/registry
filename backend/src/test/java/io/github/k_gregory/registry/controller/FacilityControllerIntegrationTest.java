package io.github.k_gregory.registry.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.k_gregory.registry.AbstractIntegrationTest;
import io.github.k_gregory.registry.dto.FacilityCreateReqeust;
import io.github.k_gregory.registry.dto.FacilityUpdateRequest;
import io.github.k_gregory.registry.model.Executant;
import io.github.k_gregory.registry.model.Facility;
import io.github.k_gregory.registry.repository.FacilityRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManager;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class FacilityControllerIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private FacilityRepository facilityRepository;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    private Facility createFacility(String name)throws Exception {
        FacilityCreateReqeust request = new FacilityCreateReqeust();
        request.setName(name);
        ResultActions actions = mvc.perform(post("/api/facility")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsBytes(request))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

        return mapper.readValue(actions.andReturn().getResponse().getContentAsString(), Facility.class);
    }

    @Test @Transactional
    public void getFacilitiesReturnsFacilities() throws Exception {
        String expectedName = "Name";
        Facility facilty = new Facility();
        facilty.setName(expectedName);
        facilityRepository.save(facilty);
        Facility faciltySecond = new Facility();
        faciltySecond.setName(expectedName + "1");
        facilityRepository.save(faciltySecond);

        mvc.perform(
            get("/api/facility")
                    .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(expectedName)));
    }

    @Test @Transactional
    public void postFacilityCreatesFacility() throws Exception {
        String expectedName = "NameCreated";
        Integer expectedHeadId = null;
        createFacility(expectedName);

        mvc.perform(
                get("/api/facility")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(expectedName)))
                .andExpect(jsonPath("$[0].headId", is(expectedHeadId)));
    }

    @Test @Transactional
    public void putFacilityUpdatesFacility() throws Exception {
        Facility created = createFacility("NameCreated");

        String expectedName = "NameUpdated";
        FacilityUpdateRequest request = new FacilityUpdateRequest();
        request.setName(expectedName);
        mvc.perform(put("/api/facility/" + created.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsBytes(request))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

        mvc.perform(
                get("/api/facility")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(expectedName)));
    }

    @Test @Transactional
    public void canAssignExecutantAsHead() throws Exception {
        Facility created = createFacility("NameCreated");

        Executant executant = new Executant();
        executant.setFirstName("123");
        executant.setMiddleName("321");
        executant.setLastName("last");
        executant.setFacility(created);
        executant.setPhoneNumber("123");
        em.persist(executant);

        FacilityUpdateRequest request = new FacilityUpdateRequest();
        request.setName("NameUpdated");
        request.setFacilityHeadId(executant.getId().intValue());

        mvc.perform(put("/api/facility/" + created.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsBytes(request))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

        mvc.perform(
                get("/api/facility")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].headId", is(executant.getId().intValue())))
                .andExpect(jsonPath("$[0].headName", is("123 321 last")));
    }
}