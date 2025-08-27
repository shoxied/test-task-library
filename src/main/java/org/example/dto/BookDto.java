package org.example.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BookDto {
    private String title;
    private String author;
    private int isbn;
}
