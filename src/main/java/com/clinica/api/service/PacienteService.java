package com.clinica.api.service;

import com.clinica.api.entity.Paciente;
import com.clinica.api.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    public List<Paciente> listar() {
        return repository.findAll();
    }

    public Paciente salvar(Paciente paciente) {
        return repository.save(paciente);
    }

    public void deletar(String id) {
        repository.deleteById(id);
    }

    public Paciente buscarPorId(String id) {
        return repository.findById(id).orElse(null);
    }
}