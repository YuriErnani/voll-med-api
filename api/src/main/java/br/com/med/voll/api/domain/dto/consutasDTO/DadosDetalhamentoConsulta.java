package br.com.med.voll.api.domain.dto.consutasDTO;

import br.com.med.voll.api.domain.model.consultas.Consulta;
import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {

    public DadosDetalhamentoConsulta(Consulta consulta) {

        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());

    }

}
