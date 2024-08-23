package sonda.med.app.model.services.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import sonda.med.app.model.entities.dto.request.ConsultaCadastroDto;
import sonda.med.app.model.repositories.PacienteRepository;

@Component
public class ValidaPacienteAtivo implements ValidadorAgendamentoDeConsulta {
	
	@Autowired
	private PacienteRepository repository;

	public void validarAgendamento(ConsultaCadastroDto dados) {
		Boolean pacienteAtivo = repository.findAtivoById(dados.pacienteId());
		
		if (!pacienteAtivo) {
			throw new ValidationException("O paciente deve estar ativo para realizar o agendamento");
		}
	}
}
