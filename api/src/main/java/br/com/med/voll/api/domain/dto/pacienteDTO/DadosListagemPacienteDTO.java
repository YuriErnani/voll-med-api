package br.com.med.voll.api.domain.dto.pacienteDTO;

import br.com.med.voll.api.domain.model.pacientes.Paciente;

public record DadosListagemPacienteDTO(Long id, String nome, String email, String cpf) {

    public DadosListagemPacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }

}
