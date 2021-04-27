package com.reto.tech.controllers;

import com.reto.tech.models.request.ClientRequest;
import com.reto.tech.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @GetMapping
    public ResponseEntity<?> index () {
        return ResponseEntity.ok(clientService.find());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void store(@Valid @RequestBody ClientRequest body) throws ParseException {
        clientService.create(body);
    }

    @GetMapping("/kpi")
    public ResponseEntity<?> average() {
        return ResponseEntity.ok(clientService.generateKpi());
    }
}
