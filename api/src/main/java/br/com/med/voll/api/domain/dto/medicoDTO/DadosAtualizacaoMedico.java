package br.com.med.voll.api.domain.dto.medicoDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,

        String nome,

        String telefone,

        @Valid
        DadosEnderecoDTO endereco) {
}
