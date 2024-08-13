package sonda.med.app.model.entities.dto.response;

import sonda.med.app.model.entities.Endereco;
import sonda.med.app.model.entities.Medico;
import sonda.med.app.model.enums.Especialidade;

public record MedicoDetalhadoResponseDto(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {

	public MedicoDetalhadoResponseDto(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
	}
}
