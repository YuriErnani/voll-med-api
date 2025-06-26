package br.com.med.voll.api.repository;

import br.com.med.voll.api.domain.model.pacientes.Paciente;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select p.ativo
            from Paciente p
            where
            p.id = :idPaciente
            """)
    Boolean findAtivoById(@NotNull Long idPaciente);

}
