package sonda.med.app.model.entities.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoDto(@NotBlank String nome, @NotBlank @Email String email, @NotBlank @Pattern(regexp = "\\+?\\d{10,15}") String telefone, @NotBlank @Pattern(regexp = "\\d{4,7}") String crm,
		@NotNull String especialidade, @NotBlank String cep, @NotBlank String numero) {

}
