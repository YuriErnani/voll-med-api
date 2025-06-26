package br.com.med.voll.api.domain.dto.pacienteDTO;

import br.com.med.voll.api.domain.dto.medicoDTO.DadosEnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosPacienteDTO(
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        String cpf,

        @NotNull
        @Valid
        DadosEnderecoDTO endereco) {
}
