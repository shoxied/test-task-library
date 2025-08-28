package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.ClientDto;
import org.example.entity.Book;
import org.example.entity.Client;
import org.example.repo.BookRepository;
import org.example.repo.ClientRepository;
import org.example.service.ClientService;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client addNewClient(ClientDto clientDto) {
        Client client = Client.builder()
                .name(clientDto.getName())
                .surname(clientDto.getSurname())
                .patronymic(clientDto.getPatronymic())
                .birth_date(clientDto.getBirthDate())
                .build();

        return clientRepository.save(client);
    }

    @Override
    public Client editClient(int id, Map<String, Object> updates) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("client with id " + id + " not found"));

        updates.forEach((field, value) -> {
            switch (field) {
                case "name":
                    client.setName((String) value);
                    break;
                case "surname":
                    client.setSurname((String) value);
                    break;
                case "patronymic":
                    client.setPatronymic((String) value);
                    break;
                case "birth_date":
                    client.setBirth_date((OffsetDateTime) value);
                    break;
                default:
                    throw new IllegalArgumentException("Field " + field + " not supported");
            }
        });

        return clientRepository.save(client);
    }

    @Override
    public List<Client> getClients() {
        return clientRepository.findAll();
    }
}
