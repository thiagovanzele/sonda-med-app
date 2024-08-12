package sonda.med.app.model.entities.dto.update;

import jakarta.validation.constraints.Pattern;

public record PacienteUpdateDto(String nome,@Pattern(regexp = "\\+?\\d{9,15}", message = "Informe um telefone valido") String telefone, String cep, String numero) {

}
