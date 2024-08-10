package sonda.med.app.model.entities.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EnderecoDto(String cep,@JsonAlias("logradouro") String rua, String complemento, String bairro, @JsonAlias("localidade")String cidade, String uf, String numero) {

}
