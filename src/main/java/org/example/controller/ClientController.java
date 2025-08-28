package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.BookDto;
import org.example.dto.ClientDto;
import org.example.entity.Book;
import org.example.entity.Client;
import org.example.service.BookService;
import org.example.service.ClientService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping(value = "addNewClient", produces = MediaType.APPLICATION_JSON_VALUE)
    public Client addNewClient(@RequestBody ClientDto clientDto){
        return clientService.addNewClient(clientDto);
    }

    @PatchMapping(value = "editClient/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Client editClient(@PathVariable Integer id,
                         @RequestBody Map<String, Object> updates) throws Exception {
        return clientService.editClient(id, updates);
    }

    @GetMapping(value = "getClients", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Client> getClients(){
        return clientService.getClients();
    }
}
