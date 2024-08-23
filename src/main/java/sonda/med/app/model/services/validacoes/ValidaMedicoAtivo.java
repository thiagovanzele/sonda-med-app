package sonda.med.app.model.services.validacoes;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ValidationException;
import sonda.med.app.model.entities.dto.request.ConsultaCadastroDto;
import sonda.med.app.model.repositories.MedicoRepository;

public class ValidaMedicoAtivo implements ValidadorAgendamentoDeConsulta {

	@Autowired
	private MedicoRepository repository;

	public void validarAgendamento(ConsultaCadastroDto dados) {
		
		if (dados.medicoId() == null) {
			return;
		}
		
		Boolean medicoAtivo = repository.findAtivoById(dados.medicoId());
		
		if (!medicoAtivo) {
			throw new ValidationException("O medico deve estar ativo para realizar o agendamento");
		}
	}
}
