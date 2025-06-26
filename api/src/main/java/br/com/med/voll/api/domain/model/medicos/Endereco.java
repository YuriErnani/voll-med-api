package br.com.med.voll.api.domain.model.medicos;

import br.com.med.voll.api.domain.dto.medicoDTO.DadosEnderecoDTO;
import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String complemento;
    private String numero;

    public Endereco(DadosEnderecoDTO endereco) {
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.cidade = endereco.cidade();
        this.uf = endereco.uf();
        this.complemento = endereco.complemento();
        this.numero = endereco.numero();
    }

    public void atualizarInformacoes(DadosEnderecoDTO dados) {

        if (dados.logradouro() != null) {
            this.logradouro = dados.logradouro();
        }
        if (dados.bairro() != null) {
            this.bairro = dados.bairro();
        }
        if (dados.cep() != null) {
            this.cep = dados.cep();
        }
        if (dados.cidade() != null) {
            this.cidade = dados.cidade();
        }
        if (dados.uf() != null) {
            this.uf = dados.uf();
        }
        if (dados.complemento() != null) {
            this.complemento = dados.complemento();
        }
        if (dados.numero() != null) {
            this.numero = dados.numero();
        }

    }

}
