package sonda.med.app.model.entities.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record MedicoDto(@NotBlank String nome, @NotBlank @Email String email, @NotBlank String telefone, @NotBlank @Pattern(regexp = "\\d{4,6}") String crm,
		@NotBlank String especialidade, @NotBlank String cep, @NotBlank String numero) {

}
