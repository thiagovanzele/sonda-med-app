package sonda.med.app.model.entities.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import sonda.med.app.model.entities.Consulta;
import sonda.med.app.model.entities.dto.request.CancelaConsultaDto;
import sonda.med.app.model.enums.MotivoCancelamento;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ConsultaResponseDto(Long id, String nomePaciente, String nomeMedico, String especialidade, LocalDateTime data, Boolean ativo, MotivoCancelamento motivoCancelamento) {
	
	public ConsultaResponseDto(Consulta consulta) {
		this(consulta.getId(), consulta.getPaciente().getNome(), consulta.getMedico().getNome(), String.valueOf(consulta.getMedico().getEspecialidade()), consulta.getData(), consulta.isAtivo(), consulta.getMotivoCancelamento());
	}
	
	public ConsultaResponseDto(Consulta consulta, CancelaConsultaDto cancelamentoDados) {
		this(consulta.getId(), consulta.getPaciente().getNome(), consulta.getMedico().getNome(), String.valueOf(consulta.getMedico().getEspecialidade()), consulta.getData(), consulta.isAtivo(), cancelamentoDados.motivo());
	}
	
	

}
