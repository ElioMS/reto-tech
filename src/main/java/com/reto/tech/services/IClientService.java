package com.reto.tech.services;

import com.reto.tech.models.entity.Client;
import com.reto.tech.models.request.ClientRequest;
import com.reto.tech.models.response.ClientKPIResponse;
import com.reto.tech.models.response.ClientResponse;

import java.text.ParseException;
import java.util.List;

public interface IClientService {

    List<ClientResponse> find();
    Client create(ClientRequest request) throws ParseException;
    ClientKPIResponse generateKpi();
}
