package sonda.med.app.model.entities.dto.response;

import java.time.LocalDateTime;

import sonda.med.app.model.entities.Consulta;

public record ConsultaResponseDto(Long id, String nomePaciente, String nomeMedico, String especialidade, LocalDateTime data) {
	
	public ConsultaResponseDto(Consulta consulta) {
		this(consulta.getId(), consulta.getPaciente().getNome(), consulta.getMedico().getNome(), String.valueOf(consulta.getMedico().getEspecialidade()), consulta.getData());
	}

}
