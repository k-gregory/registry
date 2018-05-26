package io.github.k_gregory.registry.controller;

import io.github.k_gregory.registry.AbstractIntegrationTest;
import io.github.k_gregory.registry.model.Executant;
import io.github.k_gregory.registry.model.Facility;
import io.github.k_gregory.registry.repository.ExecutantRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManager;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ExecutantControllerIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Autowired
    private EntityManager em;

    @Autowired
    private ExecutantRepository executantRepository;

    private final String FACILITY_NAME = "TestFacility";
    private Facility facility;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        facility = new Facility();
        facility.setName(FACILITY_NAME);
        em.persist(facility);
    }

    private Executant build(String name) {
        Executant e = new Executant();
        e.setFirstName(name);
        e.setMiddleName(name);
        e.setLastName(name);
        e.setFacility(facility);
        e.setPhoneNumber("+3804444444");
        return e;
    }

    @Test @Transactional
    public void getExecutantsReturnsExecutants() throws Exception {
        String expectedName = "Name";
        Executant executant = build(expectedName);
        executantRepository.save(executant);
        executantRepository.save(build("someName"));

        mvc.perform(
            get("/api/executant")
                    .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", is(expectedName)))
                .andExpect(jsonPath("$[0].middleName", is(expectedName)))
                .andExpect(jsonPath("$[0].lastName", is(expectedName)))
                .andExpect(jsonPath("$[0].facilityId", is(facility.getId().intValue())))
                .andExpect(jsonPath("$[0].facilityName", is(FACILITY_NAME)));
    }
}