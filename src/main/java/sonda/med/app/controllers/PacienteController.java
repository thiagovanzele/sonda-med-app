package sonda.med.app.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import sonda.med.app.model.entities.Paciente;
import sonda.med.app.model.entities.dto.request.PacienteCadastroDto;
import sonda.med.app.model.entities.dto.response.PacienteDetalhadoDto;
import sonda.med.app.model.entities.dto.response.PacienteResponseDto;
import sonda.med.app.model.entities.dto.update.PacienteUpdateDto;
import sonda.med.app.model.services.PacienteService;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

	@Autowired
	private PacienteService service;
	
	@GetMapping
	public ResponseEntity<Page<PacienteResponseDto>> findAll(@PageableDefault(sort = "nome", size = 10) Pageable paginacao) {
		Page<PacienteResponseDto> page = service.findAll(paginacao);
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PacienteResponseDto> findById(@PathVariable Long id) {
		Paciente paciente = service.findById(id);
		PacienteResponseDto pacienteDto = new PacienteResponseDto(paciente);
		return ResponseEntity.ok(pacienteDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PacienteDetalhadoDto> update(@PathVariable Long id, @RequestBody @Valid PacienteUpdateDto pacienteUpdate) {
		Paciente paciente = service.update(id, pacienteUpdate);
		PacienteDetalhadoDto pacienteDetalhado = new PacienteDetalhadoDto(paciente);
		return ResponseEntity.ok(pacienteDetalhado);
	}
	
	@PostMapping
	public ResponseEntity<PacienteDetalhadoDto> insert(@RequestBody @Valid PacienteCadastroDto pacienteCadastroDto) {
		Paciente paciente = service.insert(pacienteCadastroDto);
		PacienteDetalhadoDto pacienteDetalhado = new PacienteDetalhadoDto(paciente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(paciente.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(pacienteDetalhado);
	}
}
