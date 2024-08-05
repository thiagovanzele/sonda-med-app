package sonda.med.app.model.entities.dto;

import jakarta.validation.constraints.NotNull;

public record MedicoDto(@NotNull String nome, @NotNull String email,@NotNull String telefone,@NotNull String crm,@NotNull String especialidade,@NotNull String cep,@NotNull String numero) {

}
