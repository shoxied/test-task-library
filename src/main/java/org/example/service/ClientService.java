package org.example.service;

import org.example.dto.BookDto;
import org.example.dto.ClientDto;
import org.example.entity.Book;
import org.example.entity.Client;

import java.util.List;
import java.util.Map;

public interface ClientService {
    Client addNewClient(ClientDto clientDto);
    Client editClient(int id, Map<String, Object> updates) throws Exception;
    List<Client> getClients();
}
