package sonda.med.app.model.entities.dto.response;

import sonda.med.app.model.entities.Medico;

public record MedicoResponseDto(Long id, String nome, String email, String crm, String especialidade) {

	public MedicoResponseDto(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade().name());
	}
}
