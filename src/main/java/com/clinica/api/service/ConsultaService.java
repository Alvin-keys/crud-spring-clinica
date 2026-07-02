package com.clinica.api.service;

import com.clinica.api.entity.Consulta;
import com.clinica.api.repository.ConsultaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    private final ConsultaRepository repository;

    public ConsultaService(ConsultaRepository repository) {
        this.repository = repository;
    }

    public List<Consulta> listarTodas() {
        return repository.findAll();
    }

    public Consulta buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
    }

    public Consulta salvar(Consulta consulta) {
        return repository.save(consulta);
    }

    public Consulta atualizar(Long id, Consulta novaConsulta) {
        Consulta consulta = buscarPorId(id);

        consulta.setDataConsulta(novaConsulta.getDataConsulta());
        consulta.setObservacoes(novaConsulta.getObservacoes());
        consulta.setPaciente(novaConsulta.getPaciente());

        return repository.save(consulta);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public List<Consulta> buscarPorPaciente(Long pacienteId) {
        return repository.findByPacienteId(pacienteId);
    }


}