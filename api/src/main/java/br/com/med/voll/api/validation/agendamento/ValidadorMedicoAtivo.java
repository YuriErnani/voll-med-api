package br.com.med.voll.api.validation.agendamento;

import br.com.med.voll.api.domain.dto.consutasDTO.DadosAgendamentoConsulta;
import br.com.med.voll.api.infra.exception.ValidacaoException;
import br.com.med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorDeAgendamento{

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConsulta dados) {

        if(dados.idMedico() == null) {
            return;
        }

        var medicoEstaAtivo = medicoRepository.findAtivoById(dados.idMedico());

        if(!medicoEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico que não está ativo!");
        }

    }

}
