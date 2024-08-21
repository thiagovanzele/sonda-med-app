package sonda.med.app.model.services.validacoes;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import sonda.med.app.model.entities.dto.request.ConsultaCadastroDto;

@Component
public class ValidaHorarioFuncionamento implements ValidadorAgendamentoDeConsulta {

	public void validarAgendamento(ConsultaCadastroDto dados) {
		
		LocalDateTime dataConsulta = dados.dataConsulta();
		
		Boolean domingo = dataConsulta.getDayOfWeek() == DayOfWeek.SUNDAY;
		Boolean antesDaAbertura = dataConsulta.getHour() <= 7;
		Boolean depoisDoEncerramento = dataConsulta.getHour() >= 18;
		
		if (domingo || antesDaAbertura || depoisDoEncerramento) {
			throw new ValidationException("Consulta fora do horario de funcionamento");
		}
	}
}
