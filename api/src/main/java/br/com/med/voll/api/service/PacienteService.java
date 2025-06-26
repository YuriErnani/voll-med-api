package br.com.med.voll.api.service;

import br.com.med.voll.api.domain.dto.pacienteDTO.DadosAtualizacaoPaciente;
import br.com.med.voll.api.domain.dto.pacienteDTO.DadosDetalhadoPaciente;
import br.com.med.voll.api.domain.dto.pacienteDTO.DadosListagemPacienteDTO;
import br.com.med.voll.api.domain.dto.pacienteDTO.DadosPacienteDTO;
import br.com.med.voll.api.domain.model.pacientes.Paciente;
import br.com.med.voll.api.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    @Transactional
    public ResponseEntity cadastrarPacientes(DadosPacienteDTO dadosPaciente, UriComponentsBuilder uriBuilder) {

        var paciente = new Paciente(dadosPaciente);
        repository.save(paciente);

        var uri = uriBuilder
                .path("/pacientes/{id}")
                .buildAndExpand(paciente.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhadoPaciente(paciente));

    }

    public ResponseEntity<Page<DadosListagemPacienteDTO>> listarPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {

        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemPacienteDTO::new);

        return ResponseEntity.ok(page);

    }

    public DadosDetalhadoPaciente detalharPacientes(Long id) {

        var paciente = repository.getReferenceById(id);

        return new DadosDetalhadoPaciente(paciente);

    }

    @Transactional
    public DadosDetalhadoPaciente atualizarPacientes(DadosAtualizacaoPaciente dados) {

        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);

        return new DadosDetalhadoPaciente(paciente);

    }

    @Transactional
    public void excluirPacientes(Long id) {

        var paciente = repository.getReferenceById(id);
        paciente.excluir();

    }

}
