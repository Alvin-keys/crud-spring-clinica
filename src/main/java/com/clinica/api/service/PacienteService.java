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

    public Paciente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
    }

    public Paciente salvar(Paciente paciente) {
        return repository.save(paciente);
    }

    public Paciente atualizar(Long id, Paciente novoPaciente) {
        Paciente paciente = buscarPorId(id);

        paciente.setNome(novoPaciente.getNome());
        paciente.setEmail(novoPaciente.getEmail());
        paciente.setTelefone(novoPaciente.getTelefone());
        paciente.setDataPrimeiraConsulta(novoPaciente.getDataPrimeiraConsulta());
        paciente.setIdade(novoPaciente.getIdade());
        paciente.setQueixaPrincipal(novoPaciente.getQueixaPrincipal());
        paciente.setComposicaoFamiliar(novoPaciente.getComposicaoFamiliar());
        paciente.setRotina(novoPaciente.getRotina());
        paciente.setContatoEmergencia(novoPaciente.getContatoEmergencia());
        paciente.setDiasAtendimento(novoPaciente.getDiasAtendimento());
        paciente.setHorarioAtendimento(novoPaciente.getHorarioAtendimento());

        return repository.save(paciente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}