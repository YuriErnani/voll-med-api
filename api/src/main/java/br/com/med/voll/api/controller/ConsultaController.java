package br.com.med.voll.api.controller;

import br.com.med.voll.api.domain.dto.consutasDTO.DadosAgendamentoConsulta;
import br.com.med.voll.api.service.ConsultaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public ResponseEntity agendarConsulta(@RequestBody @Valid DadosAgendamentoConsulta dados) {

        var dto = consultaService.agendarConsulta(dados);

        return ResponseEntity.ok(dto);

    }

}
