package com.brimma.auth.server.controller;

import com.brimma.auth.server.entity.Client;
import com.brimma.auth.server.repository.AuthJpaRepository;
import com.brimma.auth.server.service.AuthValidator;
import com.brimma.auth.server.vo.AuthResponse;
import com.brimma.auth.server.vo.ClientData;
import com.fasterxml.jackson.databind.JsonNode;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.rsa.RSAVerifier;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
public class AuthController {
    @Autowired
    private AuthJpaRepository repository;

    @Autowired
    private AuthValidator validator;

    @PostMapping(path = "/auth", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public AuthResponse auth(@RequestParam("assertion") String assertion, @RequestParam("grant_type") String grantType) throws IOException {
        return validator.validateAuth(assertion, grantType);
    }

    @PostMapping(path = "/client/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody ClientData clientData) {
        Client client = new Client();
        client.setClientId(clientData.getClientId());
        client.setSecret(clientData.getSecret());
        client.setEmail(clientData.getEmail());
        client.setPrivateKey(clientData.getPrivateKey());
        client.setPublicKey(clientData.getPublicKey());
        repository.save(client);
    }
}
