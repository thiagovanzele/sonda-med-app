package sonda.med.app.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import sonda.med.app.model.entities.Endereco;
import sonda.med.app.model.entities.dto.request.EnderecoDto;
import sonda.med.app.model.exceptions.ValidationException;
import sonda.med.app.model.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Endereco insert(String cep, String numero) {
		Endereco endereco = enderecoRepository.findEnderecoByCep(cep);
		
		if (endereco != null && endereco.getNumero().equals(numero)) {
			return endereco;
		}				
				
		RestTemplate template = new RestTemplate();
		String url = "https://viacep.com.br/ws/" + cep + "/json/";
		ResponseEntity<EnderecoDto> response = template.getForEntity(url, EnderecoDto.class);
		
		if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
			EnderecoDto enderecoDto = response.getBody();
			endereco = new Endereco();
			endereco.setCep(cep);
			endereco.setNumero(numero);
			endereco.setUf(enderecoDto.uf());
			endereco.setRua(enderecoDto.rua());
			endereco.setBairro(enderecoDto.bairro());
			endereco.setCidade(enderecoDto.cidade());
			endereco.setComplemento(enderecoDto.complemento());
			return enderecoRepository.save(endereco);
			
		} else {
			throw new ValidationException("Cep inválido ou inexistente");
		}
	}
}
