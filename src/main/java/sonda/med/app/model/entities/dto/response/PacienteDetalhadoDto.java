package sonda.med.app.model.entities.dto.response;

import sonda.med.app.model.entities.Endereco;
import sonda.med.app.model.entities.Paciente;

public record PacienteDetalhadoDto(Long id, String nome, String email, String telefone, String cpf, Endereco endereco) {
	
	public PacienteDetalhadoDto(Paciente paciente) {
		this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
	}

}
