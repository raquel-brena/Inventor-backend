package com.rb.auth.controllers;

import com.rb.auth.domain.user.AuthenticatedDTO;
import com.rb.auth.domain.user.RegisterDTO;
import com.rb.auth.domain.user.ResponseLoginDTO;
import com.rb.auth.domain.user.User;
import com.rb.auth.infra.security.TokenService;
import com.rb.auth.repositories.UserRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    UserRepository repository;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticatedDTO data){
        var userNamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());

        var auth = this.authenticationManager.authenticate(userNamePassword);

        var token = tokenService.generateToken((User)auth.getPrincipal());

        return ResponseEntity.ok(new ResponseLoginDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
     if (this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

     String encryptedPassword = new BCryptPasswordEncoder().encode((data.password()));

     this.repository.save(new User(data.login(),encryptedPassword, data.role()));
     return ResponseEntity.ok().build();
    }

}
