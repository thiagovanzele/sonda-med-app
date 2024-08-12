package sonda.med.app.model.entities.dto.response;

import sonda.med.app.model.entities.Paciente;

public record PacienteResponseDto(String nome, String email, String cpf) {
	
	public PacienteResponseDto(Paciente paciente) {
		this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
	}

}
