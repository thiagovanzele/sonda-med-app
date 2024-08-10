package sonda.med.app.model.entities.dto.update;

import jakarta.validation.constraints.Pattern;

public record MedicoUpdateDto(String nome, @Pattern(regexp = "\\+?\\d{10,15}", message = "Formato de telefone invalido") String telefone, @Pattern(regexp = "\\d{8}", message = "O CEP deve conter 8 digitos") String cep, String numero) {

}
