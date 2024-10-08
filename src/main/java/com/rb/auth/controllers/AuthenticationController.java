package com.rb.auth.controllers;

import com.rb.auth.domain.user.User;
import com.rb.auth.domain.user.dto.AuthenticatedDTO;
import com.rb.auth.domain.user.dto.RegisterDTO;
import com.rb.auth.domain.user.dto.ResponseLoginDTO;
import com.rb.auth.infra.security.TokenService;
import com.rb.auth.repositories.UserRepository;
import com.rb.auth.services.RoleService;
import com.rb.auth.services.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RoleService roleService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticatedDTO data) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());

        var auth = this.authenticationManager.authenticate(userNamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new ResponseLoginDTO(token));
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        try {
            if (this.userRepository.findByLogin(data.login()) != null)
                throw new IllegalArgumentException("User already exists");

            String encryptedPassword = new BCryptPasswordEncoder().encode((data.password()));

            var role = roleService.findRoleByName("USER");
            var newUser = this.userRepository.save(new User(data.login(), encryptedPassword, role));

            var location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newUser.getId())
                    .toUri();

            return ResponseEntity.created(location).body("New user created with ID: " + newUser.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/logout")
    public ResponseEntity logout() {
        this.userService.logout();
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
