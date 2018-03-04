package io.github.k_gregory.registry.service;

import io.github.k_gregory.registry.AbstractIntegrationTest;
import io.github.k_gregory.registry.model.Enforcement;
import io.github.k_gregory.registry.model.Facility;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

public class EnforcementServiceIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    private EntityManager em;

    @Autowired
    private EnforcementService enforcement;

    private Facility facility;

    @Before
    public void setUp() {
        facility = new Facility();
        facility.setName("TestFacility");
        em.persist(facility);
    }

    @Test
    @Transactional
    public void listTopEnforcements_Returns_EmptyListIfNonePresent() {
        List<Enforcement> enforcements = enforcement.listTopEnforcements();
        assertThat(enforcements).isEmpty();
    }

    @Test
    @Transactional
    public void listTopEnforcements_IfLess10EnforcementsInBase_Returns_All() {
        int size = 5;
        assumeThat(size).isLessThan(10);
        saveSomeEnforcements(size);

        List<Enforcement> enforcementList = enforcement.listTopEnforcements();

        assertThat(enforcementList).size().isEqualTo(size);
    }

    @Test
    @Transactional
    public void listTopEnforcements_IfMore10EnforcementsInBase_Returns_LessThan10() {
        int size = 30;
        assumeThat(size).isGreaterThan(10);
        saveSomeEnforcements(size);

        List<Enforcement> enforcementList = enforcement.listTopEnforcements();

        assertThat(enforcementList).size().isLessThan(10 + 1);
    }

    private void saveSomeEnforcements(int count) {
        for (int i = 0; i < count; i++) {
            Enforcement enforcement = new Enforcement();
            enforcement.setFacility(facility);
            enforcement.setReceiver("TEST-RECEIVER" + i);
            enforcement.setSender("TEST-SENDER" + i);
            em.persist(enforcement);
        }
    }
}