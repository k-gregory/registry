package io.github.k_gregory.registry.service;

import io.github.k_gregory.registry.model.security.AppUser;
import io.github.k_gregory.registry.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class SpringDataUserManager implements UserDetailsManager {
    private final UserRepository userRepository;

    @Autowired
    public SpringDataUserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void createUser(UserDetails user) {
        AppUser newUser = new AppUser();

        newUser.setEnabled(user.isEnabled());
        newUser.setName(user.getUsername());
        newUser.setPassword(user.getPassword());

        userRepository.save(newUser);
    }

    @Override
    public void updateUser(UserDetails user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteUser(String username) {
        userRepository.disableUserByName(username);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Can't find " + username);
        }

        return User
                .withUsername(username)
                .password(user.getPassword())
                .disabled(!user.isEnabled())
                .authorities(new String[]{})
                .build();
    }
}
