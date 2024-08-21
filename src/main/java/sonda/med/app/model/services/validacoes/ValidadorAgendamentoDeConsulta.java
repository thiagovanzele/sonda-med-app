package sonda.med.app.model.services.validacoes;

import sonda.med.app.model.entities.dto.request.ConsultaCadastroDto;

public interface ValidadorAgendamentoDeConsulta {

	void validarAgendamento(ConsultaCadastroDto dados);
}
