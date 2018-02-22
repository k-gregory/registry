package io.github.k_gregory.registry.controller;

import io.github.k_gregory.registry.model.security.AppUser;
import io.github.k_gregory.registry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

class RegisterDTO {
    @NotNull
    @Size(min = 1, max = 50)
    private String username;

    @NotNull
    @Size(min = 4, max = 50)
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
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    //Stores the URL visited before redirection to login page
    private final HttpSessionRequestCache requestCache = new HttpSessionRequestCache();


    @Autowired
    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder,
                                  AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public String register(HttpServletRequest request,
                           HttpServletResponse response,
                           @Valid @ModelAttribute("register") RegisterDTO register,
                           BindingResult binding) {
        if (binding.hasErrors()) {
            return "register";
        }

        String username = register.getUsername();
        String password = register.getPassword();
        String encodedPassword = passwordEncoder.encode(register.getPassword());

        if (userService.userExists(username)) {
            //TODO: Show message that user already exists
            return "register";
        }


        AppUser user = userService.registerUser(username, encodedPassword);
        Set<GrantedAuthority> authorities = userService.getUserAuthorities(user);

        autologin(username, password, authorities, request);
        return getPostRegistrationRedirect(request, response);
    }

    @GetMapping("/register")
    public String register(Model m) {
        m.addAttribute("register", new RegisterDTO());
        return "register";
    }

    // Programmatically logs user in
    private void autologin(String username,
                           String password,
                           Collection<GrantedAuthority> authorities,
                           HttpServletRequest request) {
        request.getSession();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                username, password, authorities
        );
        token.setDetails(new WebAuthenticationDetails(request));
        authenticationManager.authenticate(token);
        if (token.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(token);
        }
    }

    // Returns URL user visited before being redirected to login page
    private String getPostRegistrationRedirect(HttpServletRequest request, HttpServletResponse response) {
        return Optional
                .ofNullable(requestCache.getRequest(request, response))
                .flatMap(s -> Optional.ofNullable(s.getRedirectUrl()))
                .map(url -> "redirect:" + url)
                .orElse("redirect:/profile");
    }
}
