package br.com.med.voll.api.validation.agendamento;

import br.com.med.voll.api.domain.dto.consutasDTO.DadosAgendamentoConsulta;
import br.com.med.voll.api.infra.exception.ValidacaoException;
import br.com.med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorDeAgendamento{

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dados) {

        var pacienteEstaAtivo = pacienteRepository.findAtivoById(dados.idPaciente());

        if(!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente que não existe!");
        }

    }

}
