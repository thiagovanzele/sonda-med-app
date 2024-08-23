package sonda.med.app.model.services.validacoes;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import sonda.med.app.model.entities.dto.request.ConsultaCadastroDto;

@Component
public class ValidaHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {

	public void validarAgendamento(ConsultaCadastroDto dados) {
		LocalDateTime dataConsulta = dados.dataConsulta();
		LocalDateTime dataAtual = LocalDateTime.now();
		
		long duracao = java.time.Duration.between(dataAtual, dataConsulta).toMinutes();
		if (duracao < 30) {
			throw new ValidationException("A consulta deve ser agendada com 30 minutos de antecedencia");
		}
		
		
	}
}
