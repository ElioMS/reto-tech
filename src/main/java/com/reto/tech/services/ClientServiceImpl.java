package com.reto.tech.services;

import com.reto.tech.models.entity.Client;
import com.reto.tech.models.request.ClientRequest;
import com.reto.tech.models.response.ClientKPIResponse;
import com.reto.tech.models.response.ClientResponse;
import com.reto.tech.repositories.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private IClientRepository clientRepository;

    @Override
    public List<ClientResponse> find() {
        List<Client> clients = (List<Client>) clientRepository.findAll();
        List<ClientResponse> response = new ArrayList<>();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        clients.stream().forEach(i -> {
            String date = formatter.format(i.getBirthDate());
            Random rand = new Random();
            int n = rand.nextInt(70);
            int m = rand.nextInt(12);
            int d = rand.nextInt(31);

            Calendar c = Calendar.getInstance();
            c.setTime(i.getBirthDate());
            c.add(Calendar.YEAR, n);
            c.add(Calendar.MONTH, m);
            c.add(Calendar.DATE, d);

            Date currentDatePlusOne = c.getTime();

            ClientResponse cr = new ClientResponse();
            cr.setName(i.getName());
            cr.setLastname(i.getLastname());
            cr.setBirthDate(date);
            cr.setAge(i.getAge());
            cr.setDeceaseDate(formatter.format(currentDatePlusOne));
            response.add(cr);
        });

        return response;
    }

    @Override
    public Client create(ClientRequest request) throws ParseException {
        Client client = new Client();

        Date birthDate = convertToDate(request.getBirthDate());
        Integer age = 0;

        if (request.getAge() != null) {
            age = request.getAge();
        } else {
            age = calculateAge(birthDate);
        }

        client.setName(request.getName());
        client.setLastname(request.getLastname());
        client.setBirthDate(birthDate);
        client.setAge(age);
        return clientRepository.save(client);
    }

    @Override
    public ClientKPIResponse generateKpi() {

        Integer avg = clientRepository.getClientsAgeAverage();

        ClientKPIResponse response = new ClientKPIResponse();
        response.setAverage(avg);
        response.setSd(calculateStandardDeviation(avg));

        return response;
    }

    private Double calculateStandardDeviation(Integer avg) {
        List<Integer> ages = clientRepository.getClientsAge();
        Integer size = ages.size();

        Integer sum = ages.stream().map(i -> {
            Integer difference = i - avg;
            return (int) Math.pow(difference, 2);
        }).collect(Collectors.summingInt(Integer::intValue));

        Integer div = sum / (size - 1);
        return Math.sqrt(div);
    }

    private Date convertToDate(String stringDate) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
        return date;
    }

    private Integer calculateAge(Date birthDate) {
        LocalDate birthdate = LocalDate.of(birthDate.getYear() + 1900, birthDate.getMonth() + 1 , birthDate.getDay() + 1);
        LocalDate today = LocalDate.now();

        Period p = Period.between(birthdate, today);
        return p.getYears();
    }
}
