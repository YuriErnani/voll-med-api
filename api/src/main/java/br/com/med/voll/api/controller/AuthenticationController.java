package br.com.med.voll.api.controller;

import br.com.med.voll.api.domain.dto.autenticacaoDTO.DadosAutenticacaoDTO;
import br.com.med.voll.api.domain.dto.autenticacaoDTO.DadosTokenJWT;
import br.com.med.voll.api.domain.model.usuarios.Usuario;
import br.com.med.voll.api.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacaoDTO dados) {

        var AuthenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());

        var authentication = manager.authenticate(AuthenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));

    }

}
