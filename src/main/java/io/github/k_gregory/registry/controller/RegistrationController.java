package io.github.k_gregory.registry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

class RegisterDTO {
    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

@Controller
public class RegistrationController {
    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    //Stores the URL visited before redirection to login page
    private final HttpSessionRequestCache requestCache = new HttpSessionRequestCache();


    @Autowired
    public RegistrationController(UserDetailsManager userDetailsManager,
                                  PasswordEncoder passwordEncoder,
                                  AuthenticationManager authenticationManager) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public String register(HttpServletRequest request, HttpServletResponse response, RegisterDTO register) {
        String username = register.getUsername();
        String password = register.getPassword();
        String encodedPassword = passwordEncoder.encode(register.getPassword());

        if (userDetailsManager.userExists(username)) {
            //TODO: Show message that user already exists
            return "register";
        }

        //TODO: change authorities list
        UserDetails newUser = User.builder()
                .username(username)
                .password(encodedPassword)
                .authorities(new String[]{}).build();

        userDetailsManager.createUser(newUser);

        authologin(username, password, request);
        return getPostRegistrationRedirect(request, response);
    }

    @GetMapping("/register")
    public String register(Model m) {
        m.addAttribute("register", new RegisterDTO());
        return "register";
    }

    // Programmatically logs user in
    private void authologin(String username, String password, HttpServletRequest request) {
        request.getSession();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                username, password, Collections.emptyList()
        );
        token.setDetails(new WebAuthenticationDetails(request));
        authenticationManager.authenticate(token);
        if (token.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(token);
        }
    }

    // Returns URL user visited before being redirected to login page
    private String getPostRegistrationRedirect(HttpServletRequest request, HttpServletResponse response) {
        String redirectUrl = requestCache.getRequest(request, response).getRedirectUrl();
        if (redirectUrl != null) {
            //TODO: Is this secure?
            return "redirect:" + redirectUrl;
        } else {
            return "redirect:/profile";
        }
    }
}
