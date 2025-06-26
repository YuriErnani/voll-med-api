package br.com.med.voll.api.domain.model.pacientes;

import br.com.med.voll.api.domain.model.medicos.Endereco;
import br.com.med.voll.api.domain.dto.pacienteDTO.DadosAtualizacaoPaciente;
import br.com.med.voll.api.domain.dto.pacienteDTO.DadosPacienteDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pacientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Paciente(DadosPacienteDTO dadosPaciente) {
        this.nome = dadosPaciente.nome();
        this.email = dadosPaciente.email();
        this.telefone = dadosPaciente.telefone();
        this.cpf = dadosPaciente.cpf();
        this.endereco = new Endereco(dadosPaciente.endereco());
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizacaoPaciente dadosPacienteAtualizado) {

        if (dadosPacienteAtualizado.nome() != null) {
            this.nome = dadosPacienteAtualizado.nome();
        }

        if (dadosPacienteAtualizado.telefone() != null) {
            this.telefone = dadosPacienteAtualizado.telefone();
        }

        if (dadosPacienteAtualizado.endereco() != null) {
            endereco.atualizarInformacoes(dadosPacienteAtualizado.endereco());
        }

    }

    public void excluir() {
        this.ativo = false;
    }

}
