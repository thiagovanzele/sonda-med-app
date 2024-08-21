package sonda.med.app.model.entities.dto.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import sonda.med.app.model.enums.Especialidade;

public record ConsultaCadastroDto(@NotNull Long pacienteId, 
		Long medicoId, 
		@NotNull @Future @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime dataConsulta,
		Especialidade especialidade) {

}
