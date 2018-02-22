package io.github.k_gregory.registry.service;

import io.github.k_gregory.registry.model.security.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface UserService extends UserDetailsService {
    AppUser registerUser(String username, String password);

    boolean userExists(String username);

    Set<GrantedAuthority> getUserAuthorities(AppUser user);
}
