package br.com.med.voll.api.domain.dto.pacienteDTO;

import br.com.med.voll.api.domain.model.pacientes.Paciente;

public record DadosDetalhadoPaciente(Long id, String nome, String email, String cpf, String telefone) {

    public DadosDetalhadoPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone());
    }

}
