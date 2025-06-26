package br.com.med.voll.api.validation.cancelamento;

import br.com.med.voll.api.domain.dto.consutasDTO.DadosCancelamentoConsulta;
import br.com.med.voll.api.infra.exception.ValidacaoException;
import br.com.med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorCancelamentoDeHorarioComAntecedencia implements ValidadorCancelamentoDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosCancelamentoConsulta dados) {

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());

        var agora = LocalDateTime.now();

        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("Consulta pode ser cancelada somente com antecedência mínima de 24h!");
        }

    }

}
