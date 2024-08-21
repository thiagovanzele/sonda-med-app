package sonda.med.app.model.services.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import sonda.med.app.model.entities.Paciente;
import sonda.med.app.model.entities.dto.request.ConsultaCadastroDto;
import sonda.med.app.model.services.PacienteService;

@Component
public class ValidaPacienteAtivo implements ValidadorAgendamentoDeConsulta {
	
	@Autowired
	private PacienteService service;

	public void validarAgendamento(ConsultaCadastroDto dados) {
		Paciente paciente = service.findById(dados.pacienteId());
		
		if (!paciente.isAtivo()) {
			throw new ValidationException("O paciente deve estar ativo para realizar o agendamento");
		}
	}
}
