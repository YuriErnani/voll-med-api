package br.com.med.voll.api.service;

import br.com.med.voll.api.domain.dto.consutasDTO.DadosCancelamentoConsulta;
import br.com.med.voll.api.domain.dto.consutasDTO.DadosAgendamentoConsulta;
import br.com.med.voll.api.domain.dto.consutasDTO.DadosDetalhamentoConsulta;
import br.com.med.voll.api.domain.model.consultas.Consulta;
import br.com.med.voll.api.domain.model.medicos.Medico;
import br.com.med.voll.api.infra.exception.ValidacaoException;
import br.com.med.voll.api.repository.ConsultaRepository;
import br.com.med.voll.api.repository.MedicoRepository;
import br.com.med.voll.api.repository.PacienteRepository;
import br.com.med.voll.api.validation.cancelamento.ValidadorCancelamentoDeConsulta;
import br.com.med.voll.api.validation.agendamento.ValidadorDeAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorDeAgendamento> validadores;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

    @Transactional
    public DadosDetalhamentoConsulta agendarConsulta(DadosAgendamentoConsulta dados) {

        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do médico informado não existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());

        var medico = escolherMedico(dados);

        if (medico == null) {
            throw new ValidacaoException("Não existe médico disponível nessa data!");
        }

        var consulta = new Consulta(null, medico, paciente, dados.data(), null);

        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    public void cancelar(DadosCancelamentoConsulta dados) {

        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());

        consulta.cancelar(dados.motivo());

    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {

        if(dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando o médico não for selecionado");
        }

        return medicoRepository.escolherMedicoAleatoriamente(dados.especialidade(), dados.data());

    }

}
