package br.com.med.voll.api.controller;

import br.com.med.voll.api.domain.dto.pacienteDTO.DadosAtualizacaoPaciente;
import br.com.med.voll.api.domain.dto.pacienteDTO.DadosListagemPacienteDTO;
import br.com.med.voll.api.domain.dto.pacienteDTO.DadosPacienteDTO;
import br.com.med.voll.api.service.PacienteService;
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
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @PostMapping
    public ResponseEntity cadastrarPacientes(@RequestBody @Valid DadosPacienteDTO dadosPaciente, UriComponentsBuilder uriBuilder) {
        return service.cadastrarPacientes(dadosPaciente, uriBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPacienteDTO>> listarPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return service.listarPacientes(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharPaciente(@PathVariable Long id){
        return ResponseEntity.ok(service.detalharPacientes(id));
    }

    @PutMapping
    public ResponseEntity atualizarPacientes(@RequestBody @Valid DadosAtualizacaoPaciente dadosPacienteAtualizado){
        var pacienteAtualizado = service.atualizarPacientes(dadosPacienteAtualizado);

        return ResponseEntity.ok(pacienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirPacientes(@PathVariable Long id){
        service.excluirPacientes(id);

        return ResponseEntity.noContent().build();
    }

}
