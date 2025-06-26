package br.com.med.voll.api.domain.dto.medicoDTO;

import br.com.med.voll.api.domain.model.medicos.Especialidade;
import br.com.med.voll.api.domain.model.medicos.Medico;

public record DadosListagemMedicoDTO(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DadosListagemMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

}
