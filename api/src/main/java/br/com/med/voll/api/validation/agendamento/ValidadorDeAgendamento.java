package br.com.med.voll.api.validation.agendamento;

import br.com.med.voll.api.domain.dto.consutasDTO.DadosAgendamentoConsulta;

public interface ValidadorDeAgendamento {

    void validar(DadosAgendamentoConsulta dados);

}
