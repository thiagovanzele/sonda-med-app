package sonda.med.app.model.entities.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PacienteCadastroDto(@NotBlank(message = "Nome não pode estar vazio") String nome, 
		@NotBlank(message = "Email não pode estar vazio") @Email(message = "Insira um email valido") String email, 
		@NotBlank(message = "Telefone não pode estar vazio") @Pattern(regexp = "\\+?\\d{8,12}", message = "Teleone deve conter de 8 a 12 digitos") String telefone, 
		@NotBlank(message = "Cpf não pode estar vazio") String cpf, 
		@NotBlank(message = "Cep não pode estar vazio") @Pattern(regexp = "\\d{8}", message = "Cep deve conter 8 digitos") String cep, 
		String numero) {

}
