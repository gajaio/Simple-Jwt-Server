package com.brimma.auth.server.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ClientData {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String clientId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String secret;
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String publicKey;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String privateKey;
    private boolean enabled;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
