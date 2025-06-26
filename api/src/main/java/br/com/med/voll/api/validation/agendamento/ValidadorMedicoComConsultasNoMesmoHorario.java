package br.com.med.voll.api.validation.agendamento;

import br.com.med.voll.api.domain.dto.consutasDTO.DadosAgendamentoConsulta;
import br.com.med.voll.api.infra.exception.ValidacaoException;
import br.com.med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComConsultasNoMesmoHorario implements ValidadorDeAgendamento{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados) {

        var medicoComOutraConsultaNoMesmoHorario = consultaRepository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(), dados.data());

        if(medicoComOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("Médico já possui outra consulta agendada nesse mesmo horário!");
        }

    }

}
