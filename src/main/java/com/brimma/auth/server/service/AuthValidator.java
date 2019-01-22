package com.brimma.auth.server.service;

import com.brimma.auth.server.entity.Client;
import com.brimma.auth.server.repository.AuthJpaRepository;
import com.brimma.auth.server.vo.AuthResponse;
import io.fusionauth.jwt.JWTUtils;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.rsa.RSAVerifier;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Base64;

@Component
public class AuthValidator {
    @Autowired
    private AuthJpaRepository repository;

    public AuthResponse validateAuth(String assertion, String grantType) throws IOException {
        RSAVerifier verifier = RSAVerifier.newVerifier(IOUtils.toByteArray(new ClassPathResource("private_key.txt").getInputStream()));
        JWT jwt = JWT.getDecoder().decode(assertion, verifier);

        if (validateSubject(jwt)) {
            AuthResponse authResponse = new AuthResponse();
            authResponse.setExpiresIn(3600L);
            authResponse.setAccessToken(JWTUtils.generateJWS_x5t(Base64.getEncoder().encode(assertion.getBytes())));
            return authResponse;
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    private boolean validateSubject(JWT jwt) {
        String clientId = jwt.issuer;
        Client client = repository.findByClientId(clientId);
        if (client != null && client.getEmail().equals(jwt.audience))
            return true;
        else return false;
    }
}
