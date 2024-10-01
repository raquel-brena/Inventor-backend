package com.rb.auth.controllers;

import com.rb.auth.domain.enums.Gender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/gender")
public class GenderController {

    @GetMapping
    public ResponseEntity<List<String>> getGenders() {
        var genders = Arrays.stream(Gender.values())
        .map(Gender::getGender)
        .collect(Collectors.toList());

        return ResponseEntity.ok(genders);
}
}
