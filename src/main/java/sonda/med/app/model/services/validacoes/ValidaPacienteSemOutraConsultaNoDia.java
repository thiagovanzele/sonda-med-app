package sonda.med.app.model.services.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import sonda.med.app.model.entities.dto.request.ConsultaCadastroDto;
import sonda.med.app.model.repositories.ConsultaRepository;

@Component
public class ValidaPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta {

	@Autowired
	private ConsultaRepository repository;
	
	public void validarAgendamento(ConsultaCadastroDto dados) {
		var primeiroHorario = dados.dataConsulta().withHour(7);
		var ultimoHorario = dados.dataConsulta().withHour(18);
		var pacientePossuiConsultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.pacienteId(), primeiroHorario, ultimoHorario);
		if (pacientePossuiConsultaNoDia) {
			throw new ValidationException("O Paciente já possui uma consulta agendada neste dia");
		}
	}
}
