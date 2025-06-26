package br.com.med.voll.api.domain.dto.pacienteDTO;

import br.com.med.voll.api.domain.dto.medicoDTO.DadosEnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,

        String nome,

        String telefone,

        @Valid
        DadosEnderecoDTO endereco) {
}
