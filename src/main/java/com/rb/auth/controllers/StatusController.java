package com.rb.auth.controllers;

import com.rb.auth.domain.enums.Status;
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
@RequestMapping("/api/status")
public class StatusController {

    @GetMapping
    public ResponseEntity<List<String>> getStatus() {
        var status = Arrays.stream(Status.values())
                .map(Status::getStatus)
                .collect(Collectors.toList());

        return ResponseEntity.ok(status);
    }
}
