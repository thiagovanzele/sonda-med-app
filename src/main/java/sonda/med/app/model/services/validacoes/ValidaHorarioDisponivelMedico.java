package sonda.med.app.model.services.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import sonda.med.app.model.entities.dto.request.ConsultaCadastroDto;
import sonda.med.app.model.repositories.ConsultaRepository;

@Component
public class ValidaHorarioDisponivelMedico implements ValidadorAgendamentoDeConsulta {

	@Autowired
	private ConsultaRepository repository;
	
	public void validarAgendamento(ConsultaCadastroDto dados) {
		
		Boolean medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(dados.medicoId(), dados.dataConsulta());
		if (medicoPossuiOutraConsultaNoMesmoHorario) {
			throw new ValidationException("Médico já possuí consulta agendada para esse horario");
		}
		

	}
}
