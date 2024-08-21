package sonda.med.app.model.repositories;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import sonda.med.app.model.entities.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

	Boolean existsByPacienteIdAndDataBetween(@NotNull Long pacienteId, LocalDateTime primeiroHorario,
			LocalDateTime ultimoHorario);

	Boolean existsByMedicoIdAndData(Long medicoId, @NotNull @Future LocalDateTime dataConsulta);

}
