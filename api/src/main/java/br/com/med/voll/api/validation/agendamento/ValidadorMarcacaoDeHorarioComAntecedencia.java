package br.com.med.voll.api.validation.agendamento;

import br.com.med.voll.api.domain.dto.consutasDTO.DadosAgendamentoConsulta;
import br.com.med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorMarcacaoDeHorarioComAntecedencia implements ValidadorDeAgendamento{

    public void validar(DadosAgendamentoConsulta dados) {

        var dataConsulta = dados.data();

        var agora = LocalDateTime.now();

        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if(diferencaEmMinutos < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos!");
        }

    }

}
