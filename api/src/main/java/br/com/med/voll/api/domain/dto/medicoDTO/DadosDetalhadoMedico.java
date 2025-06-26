package br.com.med.voll.api.domain.dto.medicoDTO;

import br.com.med.voll.api.domain.model.medicos.Endereco;
import br.com.med.voll.api.domain.model.medicos.Especialidade;
import br.com.med.voll.api.domain.model.medicos.Medico;

public record DadosDetalhadoMedico(Long id, String nome, String email, String crm, Especialidade especialidade, Endereco endereco) {

    public DadosDetalhadoMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }

}
