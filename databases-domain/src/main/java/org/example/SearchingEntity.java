package org.example;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@Entity
@Data
@Table(name = "SEARCHING")
@SuperBuilder
@RequiredArgsConstructor
@AllArgsConstructor
public class SearchingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEARCHING_TABLE_ID_SEQ")
    @SequenceGenerator(name = "SEARCHING_TABLE_ID_SEQ", sequenceName = "SEARCHING_TABLE_ID_SEQ", allocationSize = 1)
    private Long id;
    private String searchingType;
    private String searchingTime;
}
