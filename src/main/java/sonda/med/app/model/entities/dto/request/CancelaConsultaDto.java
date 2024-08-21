package sonda.med.app.model.entities.dto.request;

import jakarta.validation.constraints.NotNull;
import sonda.med.app.model.enums.MotivoCancelamento;

public record CancelaConsultaDto(@NotNull MotivoCancelamento motivo) {

}
