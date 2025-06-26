package br.com.med.voll.api.domain.dto.consutasDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(
        @NotNull
        Long idConsulta,

        @NotBlank
        String motivo) {
}
