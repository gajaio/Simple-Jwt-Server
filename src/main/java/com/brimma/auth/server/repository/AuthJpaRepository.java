package com.brimma.auth.server.repository;

import com.brimma.auth.server.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthJpaRepository extends JpaRepository<Client, String> {
    @Query("SELECT c FROM Client c WHERE clientId=:clientId")
    public Client findByClientId(String clientId);
}
