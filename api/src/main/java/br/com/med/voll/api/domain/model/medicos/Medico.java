package br.com.med.voll.api.domain.model.medicos;

import br.com.med.voll.api.domain.dto.medicoDTO.DadosAtualizacaoMedico;
import br.com.med.voll.api.domain.dto.medicoDTO.DadosMedicoDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Medico(DadosMedicoDTO dadosMedico) {
        this.nome = dadosMedico.nome();
        this.email = dadosMedico.email();
        this.telefone = dadosMedico.telefone();
        this.crm = dadosMedico.crm();
        this.especialidade = dadosMedico.especialidade();
        this.endereco = new Endereco(dadosMedico.endereco());
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dadosMedicoAtualizado) {

        if (dadosMedicoAtualizado.nome() != null) {
            this.nome = dadosMedicoAtualizado.nome();
        }
        if (dadosMedicoAtualizado.telefone() != null) {
            this.telefone = dadosMedicoAtualizado.telefone();
        }
        if (dadosMedicoAtualizado.endereco() != null) {
            this.endereco.atualizarInformacoes(dadosMedicoAtualizado.endereco());
        }

    }

    public void excluir() {
        this.ativo = false;
    }

}
