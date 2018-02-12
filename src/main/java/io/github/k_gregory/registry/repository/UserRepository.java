package io.github.k_gregory.registry.repository;

import io.github.k_gregory.registry.model.security.AppUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<AppUser, Long> {
    AppUser findByName(String name);

    boolean existsByName(String name);

    @Query("update AppUser set enabled=false where name = :name")
    @Modifying
    void disableUserByName(@Param("name") String name);
}
