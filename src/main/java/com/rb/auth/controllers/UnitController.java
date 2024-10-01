package com.rb.auth.controllers;

import com.rb.auth.domain.enums.Unit;
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
@RequestMapping("/api/unit")
public class UnitController {

    @GetMapping
    public ResponseEntity<List<String>> getStatus() {
        var units = Arrays.stream(Unit.values())
                .map(Unit::getUnit)
                .collect(Collectors.toList());

        return ResponseEntity.ok(units);
    }
}
