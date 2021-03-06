package io.github.k_gregory.registry.service;

import io.github.k_gregory.registry.AbstractIntegrationTest;
import io.github.k_gregory.registry.dto.TopEnforcementDTO;
import io.github.k_gregory.registry.model.Enforcement;
import io.github.k_gregory.registry.model.EnforcementState;
import io.github.k_gregory.registry.model.Facility;
import io.github.k_gregory.registry.model.Subject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
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
        List<TopEnforcementDTO> topEnforcements = enforcement.listTopEnforcements();
        assertThat(topEnforcements).isEmpty();
    }

    @Test
    @Transactional
    public void listTopEnforcements_IfLess10EnforcementsInBase_Returns_All() {
        int size = 5;
        assumeThat(size).isLessThan(10);
        saveSomeEnforcements(size);

        List<TopEnforcementDTO> topEnforcements = enforcement.listTopEnforcements();

        assertThat(topEnforcements).size().isEqualTo(size);
    }

    @Test
    @Transactional
    public void listTopEnforcements_IfMore10EnforcementsInBase_Returns_LessThan10() {
        int size = 30;
        assumeThat(size).isGreaterThan(10);
        saveSomeEnforcements(size);

        List<TopEnforcementDTO> topEnforcements = enforcement.listTopEnforcements();

        assertThat(topEnforcements).size().isLessThan(10 + 1);
    }

    @Test @Transactional
    public void listTopEnforcements_Returns_EnforcementsFromBase(){
        Facility facility = new Facility();
        facility.setName("Zee-Facility");
        em.persist(facility);

        Enforcement newEnforcement = new Enforcement();
        Subject r = new Subject();
        r.setName("ZEE-RECEIVER");
        r.setInn("REC");
        Subject s = new Subject();
        s.setName("ZEE-SENDER");
        s.setInn("SEN");
        em.persist(r);
        em.persist(s);
        newEnforcement.setFacility(facility);
        newEnforcement.setReceiver(r);
        newEnforcement.setSender(s);
        newEnforcement.setStartedAt(new Date(42));
        newEnforcement.setState(EnforcementState.CLOSED);
        em.persist(newEnforcement);

        List<TopEnforcementDTO> topEnforcements = enforcement.listTopEnforcements();

        assertThat(topEnforcements).size().isEqualTo(1);
        assertThat(topEnforcements).first().satisfies(enforcement->{
            assertThat(enforcement.getId()).isEqualTo(newEnforcement.getId());
            assertThat(enforcement.getSender()).isEqualTo("ZEE-SENDER");
            assertThat(enforcement.getReceiver()).isEqualTo("ZEE-RECEIVER");
            assertThat(enforcement.getFacilityName()).isEqualTo("Zee-Facility");
            assertThat(enforcement.getStartedAt().getTime()).isEqualTo(new Date(42).getTime());
            assertThat(enforcement.getState()).isEqualTo(EnforcementState.CLOSED);
        });
    }

    private void saveSomeEnforcements(int count) {
        for (int i = 0; i < count; i++) {
            Subject r = new Subject();
            r.setInn("2" + i);
            r.setName("TEST-RECEIVER" + i);
            Subject s = new Subject();
            s.setName("TEST-SENDER" + i);
            s.setInn("3" + i);
            Enforcement enforcement = new Enforcement();
            em.persist(r);
            em.persist(s);
            enforcement.setFacility(facility);
            enforcement.setReceiver(r);
            enforcement.setSender(s);
            enforcement.setStartedAt(new Date(0));
            enforcement.setState(EnforcementState.CLOSED);
            em.persist(enforcement);
        }
    }
}