package com.java.assessment.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String no;

    private String name;

    private int authorId;

    private String type;
}
