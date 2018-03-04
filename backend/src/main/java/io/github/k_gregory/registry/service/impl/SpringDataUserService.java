package io.github.k_gregory.registry.service.impl;

import io.github.k_gregory.registry.model.security.AppUser;
import io.github.k_gregory.registry.model.security.Group;
import io.github.k_gregory.registry.repository.GroupRepository;
import io.github.k_gregory.registry.repository.UserRepository;
import io.github.k_gregory.registry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SpringDataUserService implements UserService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public SpringDataUserService(UserRepository userRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }


    @Override
    public AppUser registerUser(String username, String password) {
        Group group = groupRepository
                .getByName("REGISTERED_USER")
                .orElseThrow(() -> new RuntimeException("Can't find REGISTERED_USER group"));
        AppUser newUser = new AppUser();

        newUser.setEnabled(true);
        newUser.setName(username);
        newUser.setPassword(password);
        newUser.getGroups().add(group);

        return userRepository.save(newUser);
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByName(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<GrantedAuthority> getUserAuthorities(AppUser user) {
        return Stream
                .concat(
                        userRepository.listGroupAuthoritiesByUsername(user.getName()),
                        user.getOwnAuthorities().stream())
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Can't find " + username);
        }

        return User
                .withUsername(username)
                .password(user.getPassword())
                .disabled(!user.isEnabled())
                .authorities(getUserAuthorities(user))
                .build();
    }
}
