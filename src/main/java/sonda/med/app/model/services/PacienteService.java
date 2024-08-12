package sonda.med.app.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import sonda.med.app.model.entities.Endereco;
import sonda.med.app.model.entities.Paciente;
import sonda.med.app.model.entities.dto.request.CpfResponseDto;
import sonda.med.app.model.entities.dto.request.PacienteCadastroDto;
import sonda.med.app.model.entities.dto.response.PacienteResponseDto;
import sonda.med.app.model.entities.dto.update.PacienteUpdateDto;
import sonda.med.app.model.exceptions.ResourceNotFoundException;
import sonda.med.app.model.repositories.PacienteRepository;

@Service
public class PacienteService {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	public Page<PacienteResponseDto> findAll(Pageable paginacao) {
		return pacienteRepository.findAll(paginacao).map(PacienteResponseDto::new);
	}
	
	public Paciente findById(Long id) {
		return pacienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, Paciente.class));
	}
	
	@Transactional
	public void delete(Long id) {
		Paciente paciente = findById(id);
		inativarPaciente(paciente);
		pacienteRepository.save(paciente);
	}
	
	private void inativarPaciente(Paciente paciente) {
		paciente.setAtivo(false);
	}
	
	@Transactional
	public Paciente update(Long id, PacienteUpdateDto pacienteUpdateDto) {
		Paciente paciente = findById(id);
		updateData(paciente, pacienteUpdateDto);
		return pacienteRepository.save(paciente);
	}
	
	private void updateData(Paciente paciente, PacienteUpdateDto pacienteUpdateDto) {
		if (pacienteUpdateDto.telefone() != null && pacienteUpdateDto.telefone().equals("")) {
			throw new ValidationException("O telefone não pode estar em branco");
		}
		
		if (pacienteUpdateDto.nome() != null && !pacienteUpdateDto.nome().isBlank()) {
			paciente.setNome(pacienteUpdateDto.nome());
		}
		
		
		if (pacienteUpdateDto.telefone() != null && !pacienteUpdateDto.telefone().isBlank()) {
			paciente.setTelefone(pacienteUpdateDto.telefone());
		}

		if (pacienteUpdateDto.cep() != null && !pacienteUpdateDto.cep().isBlank()) {
			Endereco end = enderecoService.insert(pacienteUpdateDto.cep(), pacienteUpdateDto.numero());
			paciente.setEndereco(end);
		}
		
	}

	@Transactional
	public Paciente insert(PacienteCadastroDto pacienteCadastroDto) {
		if (!validaCpf(pacienteCadastroDto.cpf())) {
			throw new ValidationException("Cpf inválido");
		}
		
		Endereco end = enderecoService.insert(pacienteCadastroDto.cep(), pacienteCadastroDto.numero());
		
		Paciente paciente = new Paciente();
		paciente.setNome(pacienteCadastroDto.nome());
		paciente.setEmail(pacienteCadastroDto.email());
		paciente.setCpf(pacienteCadastroDto.cpf());
		paciente.setTelefone(pacienteCadastroDto.telefone());
		paciente.setEndereco(end);
		paciente.setAtivo(true);
		
		return pacienteRepository.save(paciente);
	}
	
	private boolean validaCpf(String cpf) {
	    RestTemplate restTemplate = new RestTemplate();
	    String url = "https://api.invertexto.com/v1/validator?value=" + cpf + "&type=cpf";
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Authorization", "Bearer 14929|a5OGwSTbrSbYymGDsLeeRwKdxgqpH6M9");
	    HttpEntity<String> entity = new HttpEntity<>(headers);

	    try {
	        ResponseEntity<CpfResponseDto> response = restTemplate.exchange(url, HttpMethod.GET, entity, CpfResponseDto.class);
	        
	        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
	            CpfResponseDto cpfResponse = response.getBody();
	            return "true".equals(cpfResponse.valid());
	        } else {
	            throw new ValidationException("Erro ao consultar o CPF");
	        }
	    } catch (HttpClientErrorException e) {
	        throw new ValidationException("Erro ao consultar o CPF: " + e.getMessage());
	    }
	}

	
}
