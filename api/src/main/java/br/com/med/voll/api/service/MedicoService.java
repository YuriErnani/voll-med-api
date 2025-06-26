package br.com.med.voll.api.service;

import br.com.med.voll.api.domain.dto.medicoDTO.DadosDetalhadoMedico;
import br.com.med.voll.api.domain.dto.medicoDTO.DadosListagemMedicoDTO;
import br.com.med.voll.api.domain.dto.medicoDTO.DadosAtualizacaoMedico;
import br.com.med.voll.api.domain.dto.medicoDTO.DadosMedicoDTO;
import br.com.med.voll.api.domain.model.medicos.Medico;
import br.com.med.voll.api.repository.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    @Transactional
    public ResponseEntity cadastrarMedicos(@RequestBody @Valid DadosMedicoDTO dadosMedico, UriComponentsBuilder uriBuilder) {

        var medico = new Medico(dadosMedico);
        repository.save(medico);

        var uri = uriBuilder
                .path("/medicos/{id}")
                .buildAndExpand(medico.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhadoMedico(medico));

    }

    public ResponseEntity<Page<DadosListagemMedicoDTO>> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {

        var page = repository.findAllByAtivoTrue(paginacao)
                .map(DadosListagemMedicoDTO::new);

        return ResponseEntity.ok(page);

    }

    public ResponseEntity detalharMedicos(@PathVariable Long id) {

        var medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhadoMedico(medico));

    }

    @Transactional
    public ResponseEntity atualizarMedicos(@RequestBody @Valid DadosAtualizacaoMedico dadosMedicoAtualizado) {

        var medico = repository.getReferenceById(dadosMedicoAtualizado.id());
        medico.atualizarInformacoes(dadosMedicoAtualizado);

        return ResponseEntity.ok(new DadosDetalhadoMedico(medico));

    }

    @Transactional
    public ResponseEntity excluirMedicos(@PathVariable Long id) {

        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();

    }

}
