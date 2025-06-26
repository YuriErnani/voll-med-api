package br.com.med.voll.api.repository;

import br.com.med.voll.api.domain.model.consultas.Consulta;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    Boolean existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(Long idMedico, LocalDateTime data);

    Boolean existsByPacienteIdAndDataBetween(@NotNull Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

}
