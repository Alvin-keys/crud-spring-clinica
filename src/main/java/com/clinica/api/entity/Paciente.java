package com.clinica.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private LocalDate dataPrimeiraConsulta;

    private Integer idade;
    private String queixaPrincipal;
    private String composicaoFamiliar;
    private String rotina;
    private String contatoEmergencia;
    private String diasAtendimento;
    private String horarioAtendimento;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Consulta> consultas;
}