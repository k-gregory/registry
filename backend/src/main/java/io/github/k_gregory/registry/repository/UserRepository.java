package io.github.k_gregory.registry.repository;

import io.github.k_gregory.registry.model.security.AppUser;
import io.github.k_gregory.registry.model.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.stream.Stream;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByName(String name);

    boolean existsByName(String name);

    @Query("select a from AppUser u join u.groups g join g.authorities a where u.name = :name")
    Stream<Authority> listGroupAuthoritiesByUsername(@Param("name") String name);
}
