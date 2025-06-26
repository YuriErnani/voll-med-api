package br.com.med.voll.api.validation.cancelamento;

import br.com.med.voll.api.domain.dto.consutasDTO.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {

    void validar(DadosCancelamentoConsulta dados);

}
