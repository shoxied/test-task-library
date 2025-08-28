package org.example.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ClientDto {
    private String name;
    private String surname;
    private String patronymic;
    private OffsetDateTime birthDate;
}
