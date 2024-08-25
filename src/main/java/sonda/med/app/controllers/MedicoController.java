package sonda.med.app.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import sonda.med.app.model.entities.Medico;
import sonda.med.app.model.entities.dto.request.MedicoCadastroDto;
import sonda.med.app.model.entities.dto.response.MedicoDetalhadoResponseDto;
import sonda.med.app.model.entities.dto.response.MedicoResponseDto;
import sonda.med.app.model.entities.dto.update.MedicoUpdateDto;
import sonda.med.app.model.services.MedicoService;

@Validated
@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {
	
	@Autowired
	private MedicoService service;
	
	@PostMapping
	public ResponseEntity<MedicoDetalhadoResponseDto> insert(@Valid @RequestBody MedicoCadastroDto medicoDto) {
		Medico medico = service.insert(medicoDto);
		MedicoDetalhadoResponseDto medicoDetalhado = new MedicoDetalhadoResponseDto(medico);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(medicoDetalhado.id())
				.toUri();
		
		return ResponseEntity.created(uri).body(medicoDetalhado);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MedicoResponseDto> findById(@PathVariable Long id) {
		MedicoResponseDto medico = service.findById(id);
		
		return ResponseEntity.ok(medico);
	}
	
	@GetMapping
	public ResponseEntity<Page<MedicoResponseDto>> findAll(@PageableDefault(sort = "nome", size = 10) Pageable pageable) {
		Page<MedicoResponseDto> list = service.findAll(pageable);
		return ResponseEntity.ok(list);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MedicoDetalhadoResponseDto> update(@PathVariable Long id, @RequestBody @Valid MedicoUpdateDto medicoDto) {
		Medico medico = service.update(id, medicoDto);
		MedicoDetalhadoResponseDto medicoDetalhado = new MedicoDetalhadoResponseDto(medico);
		return ResponseEntity.ok(medicoDetalhado);
	}

}
