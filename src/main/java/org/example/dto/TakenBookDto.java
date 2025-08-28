package org.example.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TakenBookDto {
    private String name;
    private String surname;
    private String patronymic;
    private OffsetDateTime birthDate;
    private String title;
    private String author;
    private int isbn;
    private OffsetDateTime issueDate;
}
