package com.clinica.api.controller;

import com.clinica.api.entity.Paciente;
import com.clinica.api.service.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Paciente> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public Paciente salvar(@RequestBody Paciente paciente) {
        return service.salvar(paciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizar(
            @PathVariable Long id,
            @RequestBody Paciente paciente) {
        return ResponseEntity.ok(service.atualizar(id, paciente));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}