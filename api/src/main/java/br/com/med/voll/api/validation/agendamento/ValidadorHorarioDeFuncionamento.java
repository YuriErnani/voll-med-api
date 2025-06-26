package br.com.med.voll.api.validation.agendamento;

import br.com.med.voll.api.domain.dto.consutasDTO.DadosAgendamentoConsulta;
import br.com.med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioDeFuncionamento implements ValidadorDeAgendamento{

    public void validar(DadosAgendamentoConsulta dados) {

        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAbertura = dataConsulta.getHour() < 7;
        var depoisDoEncerramento = dataConsulta.getHour() > 18;

        if(domingo || antesDaAbertura || depoisDoEncerramento) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica!");
        }

    }

}
