package com.clinica.api.controller;

import com.clinica.api.entity.Paciente;
import com.clinica.api.service.PacienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
@CrossOrigin(origins = "*") // libera Angular
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Paciente> listar() {
        return service.listar();
    }

    @PostMapping
    public Paciente salvar(@RequestBody Paciente paciente) {
        return service.salvar(paciente);
    }

    @GetMapping("/{id}")
    public Paciente buscarPorId(@PathVariable String id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deletar(id);
    }

    @PutMapping("/{id}")
    public Paciente atualizar(@PathVariable String id, @RequestBody Paciente paciente) {
        paciente.setId(id);
        return service.salvar(paciente);
    }
}