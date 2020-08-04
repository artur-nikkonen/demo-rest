package com.flamexander.demo.rest.repositories;

import com.flamexander.demo.rest.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
