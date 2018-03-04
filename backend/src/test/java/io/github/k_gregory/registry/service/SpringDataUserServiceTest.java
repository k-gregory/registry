package io.github.k_gregory.registry.service;

import io.github.k_gregory.registry.AbstractIntegrationTest;
import io.github.k_gregory.registry.model.security.AppUser;
import io.github.k_gregory.registry.model.security.Authority;
import io.github.k_gregory.registry.model.security.Group;
import io.github.k_gregory.registry.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;


public class SpringDataUserServiceTest extends AbstractIntegrationTest {
    @Autowired
    private EntityManager em;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private Authority[] a;
    private Group[] g;
    private AppUser[] u;

    private void fillAuthorities() {
        a = new Authority[4];

        for (int i = 0; i < a.length; i++) {
            Authority authority = new Authority();
            a[i] = authority;
            authority.setName("a" + i);
            em.persist(authority);
        }
    }

    private void fillGroups() {
        g = new Group[3];

        for (int i = 0; i < g.length; i++) {
            Group group = new Group();
            g[i] = group;
            group.setName("g" + i);
            em.persist(group);
        }
    }

    private void fillUsers() {
        u = new AppUser[4];

        for (int i = 0; i < u.length; i++) {
            AppUser user = new AppUser();
            u[i] = user;
            user.setName("u" + i);
            user.setEnabled(true);
            user.setPassword(Integer.toString(i));
            em.persist(user);
        }
    }

    // g0: a0
    // g1: a0, a1
    // g2: a2

    // u0: g0, a2 => a0, a2
    // u1: g1, a2 => a0, a1, a2
    // u2: a2, a3 => a2, a3
    // u3: g1, g2 => a0, a1, a2

    private void setUpRelations() {
        g[0].getAuthorities().add(a[0]);
        g[1].getAuthorities().add(a[0]);
        g[1].getAuthorities().add(a[1]);
        g[2].getAuthorities().add(a[2]);

        u[0].getGroups().add(g[0]);
        u[0].getOwnAuthorities().add(a[2]);
        u[1].getGroups().add(g[1]);
        u[1].getOwnAuthorities().add(a[2]);
        u[2].getOwnAuthorities().add(a[2]);
        u[2].getOwnAuthorities().add(a[3]);
        u[3].getGroups().add(g[1]);
        u[3].getGroups().add(g[2]);
        merge();
    }

    @Before
    @Transactional
    public void setUp() {
        fillAuthorities();
        fillGroups();
        fillUsers();
        setUpRelations();
    }

    private void merge() {
        Stream.of(g, u).flatMap(Arrays::stream).forEach(em::merge);
    }

    @Test
    @Transactional
    public void getUserAuthorities_WhenAddingAuthoritiesToUsersAndGroups_ReturnsAllAuthoritiesOfUser() {
        ArrayList<Set<GrantedAuthority>> grants = new ArrayList<>();
        for (AppUser anU : u) grants.add(userService.getUserAuthorities(userRepository.getOne(anU.getId())));
        assertThat(grants.get(0)).hasSameElementsAs(createAuthorityList("a0", "a2"));
        assertThat(grants.get(1)).hasSameElementsAs(createAuthorityList("a0", "a1", "a2"));
        assertThat(grants.get(2)).hasSameElementsAs(createAuthorityList("a2", "a3"));
        assertThat(grants.get(3)).hasSameElementsAs(createAuthorityList("a0", "a1", "a2"));
    }
}