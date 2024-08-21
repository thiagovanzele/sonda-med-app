package sonda.med.app.model.services.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import sonda.med.app.model.entities.Medico;
import sonda.med.app.model.entities.dto.request.ConsultaCadastroDto;
import sonda.med.app.model.repositories.MedicoRepository;

@Component
public class ValidaMedicoAtivo implements ValidadorAgendamentoDeConsulta {

	@Autowired
	private MedicoRepository repository;

	public void validarAgendamento(ConsultaCadastroDto dados) {
		
		Medico medico = repository.getReferenceById(dados.medicoId());
		
		if (!medico.isAtivo()) {
			throw new ValidationException("O medico deve estar ativo para realizar o agendamento");
		}
	}
}
