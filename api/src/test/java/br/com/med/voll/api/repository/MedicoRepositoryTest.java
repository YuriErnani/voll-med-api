package br.com.med.voll.api.repository;

import br.com.med.voll.api.domain.dto.medicoDTO.DadosEnderecoDTO;
import br.com.med.voll.api.domain.dto.medicoDTO.DadosMedicoDTO;
import br.com.med.voll.api.domain.dto.pacienteDTO.DadosPacienteDTO;
import br.com.med.voll.api.domain.model.consultas.Consulta;
import br.com.med.voll.api.domain.model.medicos.Especialidade;
import br.com.med.voll.api.domain.model.medicos.Medico;
import br.com.med.voll.api.domain.model.pacientes.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Deveria delvolver null caso o único médico cadastrado não estivesse disponível na data selecionada")
    void escolherMedicoAleatoriamenteCenario1() {

        var data = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente", "paciente@email.com", "00000000000");

        cadastrarConsulta(medico, paciente, data);

        var medicoDisponivel = medicoRepository.escolherMedicoAleatoriamente(Especialidade.CARDIOLOGIA, data);

        assertThat(medicoDisponivel).isNull();

    }

    @Test
    @DisplayName("Deveria delvolver médico caso ele estivesse disponível na data selecionada")
    void escolherMedicoAleatoriamenteCenario2() {

        var data = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);

        var medicoDisponivel = medicoRepository.escolherMedicoAleatoriamente(Especialidade.CARDIOLOGIA, data);

        assertThat(medicoDisponivel).isEqualTo(medico);

    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        entityManager.persist(new Consulta(null, medico, paciente, data, null));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        entityManager.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        entityManager.persist(paciente);
        return paciente;
    }

    private DadosMedicoDTO dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosMedicoDTO(
                nome,
                email,
                "81999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosPacienteDTO dadosPaciente(String nome, String email, String cpf) {
        return new DadosPacienteDTO(
                nome,
                email,
                "81999999999",
                cpf,
                dadosEndereco()
        );
    }

    private DadosEnderecoDTO dadosEndereco() {
        return new DadosEnderecoDTO(
                "Rua X",
                "Bairro Y",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }

}
