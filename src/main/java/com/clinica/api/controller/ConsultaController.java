package com.clinica.api.controller;

import com.clinica.api.entity.Consulta;
import com.clinica.api.service.ConsultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService service;

    public ConsultaController(ConsultaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Consulta> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Consulta> criar(@RequestBody Consulta consulta) {
        return ResponseEntity.ok(service.salvar(consulta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> atualizar(
            @PathVariable Long id,
            @RequestBody Consulta consulta) {
        return ResponseEntity.ok(service.atualizar(id, consulta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/paciente/{id}")
    public List<Consulta> buscarPorPaciente(@PathVariable Long id) {
        return service.buscarPorPaciente(id);
    }
}