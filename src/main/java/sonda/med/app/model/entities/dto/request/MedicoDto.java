package sonda.med.app.model.entities.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record MedicoDto(
		@NotBlank(message = "O nome não pode estar em branco") String nome,
		@NotBlank(message = "O email não pode estar em branco") @Email(message = "O email deve ser válido") String email,
		@NotBlank(message = "O telefone não pode estar em branco") @Pattern(regexp = "\\+?\\d{10,15}", message = "O telefone deve corresponder ao formato: +1234567890 ou 1234567890") String telefone,
		@NotBlank(message = "O CRM não pode estar em branco") String crm,
		@NotBlank(message = "A especialidade não pode estar em branco") String especialidade,
		@NotBlank(message = "O CEP não pode estar em branco") @Pattern(regexp = "\\d{8}", message = "O CEP deve conter 8 digitos") String cep,
		@NotBlank(message = "O número não pode estar em branco") String numero) {
}
