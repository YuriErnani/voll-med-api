package br.com.med.voll.api.controller;

import br.com.med.voll.api.domain.dto.medicoDTO.DadosListagemMedicoDTO;
import br.com.med.voll.api.domain.dto.medicoDTO.DadosAtualizacaoMedico;
import br.com.med.voll.api.domain.dto.medicoDTO.DadosMedicoDTO;
import br.com.med.voll.api.service.MedicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @PostMapping
    public ResponseEntity cadastrarMedicos(@RequestBody @Valid DadosMedicoDTO dadosMedico, UriComponentsBuilder uriBuilder) {
        return service.cadastrarMedicos(dadosMedico, uriBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicoDTO>> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return service.listarMedicos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharMedicos(@PathVariable Long id) {
        return service.detalharMedicos(id);
    }

    @PutMapping
    public ResponseEntity atualizarMedicos(@RequestBody @Valid DadosAtualizacaoMedico dadosMedicoAtualizado) {
        return service.atualizarMedicos(dadosMedicoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirMedicos(@PathVariable Long id) {
        return service.excluirMedicos(id);
    }

}
