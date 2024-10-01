package com.rb.auth.controllers;


import com.rb.auth.domain.user.User;
import com.rb.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @Autowired
    UserRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        var user = getUserById(id);

        //CreateProductResponseDTO productDto = new CreateProductResponseDTO(product);
        return ResponseEntity.ok(user);
    }

    public User getUserById (String id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new Error("User doesnt exists"));
    }
}
