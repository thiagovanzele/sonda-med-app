package sonda.med.app.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jakarta.validation.constraints.NotNull;
import sonda.med.app.model.entities.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

	@Query("""
			select p.ativo
			from Paciente p
			where
			p.id = :pacienteId
			""")
	Boolean findAtivoById(@NotNull Long pacienteId);

	

}
