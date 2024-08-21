package sonda.med.app.model.entities.dto.response;

import java.time.LocalDateTime;

import sonda.med.app.model.entities.Consulta;
import sonda.med.app.model.entities.dto.request.CancelaConsultaDto;
import sonda.med.app.model.enums.MotivoCancelamento;

public record ConsultaCancelamentoResponseDto (String medico, String paciente, LocalDateTime data, MotivoCancelamento motivo) {
		

	public ConsultaCancelamentoResponseDto(CancelaConsultaDto cancelaConsulta, Consulta consulta) {
		this(consulta.getMedico().getNome(), consulta.getPaciente().getNome(), consulta.getData(), cancelaConsulta.motivo());
	}
}
