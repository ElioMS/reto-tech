package com.reto.tech.repositories;

import com.reto.tech.models.entity.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IClientRepository extends CrudRepository<Client, Long> {

    @Query(value = "SELECT AVG(age) FROM clients", nativeQuery = true)
    Integer getClientsAgeAverage();

    @Query(value = "SELECT (age) FROM clients", nativeQuery = true)
    List<Integer> getClientsAge();
}
