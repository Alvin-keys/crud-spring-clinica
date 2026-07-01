package com.clinica.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nome;
    private String dataPrimeiraConsulta;
    private String telefone;
    private String email;
    private String uf;
    private String municipio;
}