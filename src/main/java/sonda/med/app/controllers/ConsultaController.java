package sonda.med.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sonda.med.app.model.entities.Consulta;
import sonda.med.app.model.entities.dto.request.CancelaConsultaDto;
import sonda.med.app.model.entities.dto.request.ConsultaCadastroDto;
import sonda.med.app.model.entities.dto.response.ConsultaCancelamentoResponseDto;
import sonda.med.app.model.entities.dto.response.ConsultaResponseDto;
import sonda.med.app.model.services.ConsultaService;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

	@Autowired
	private ConsultaService service;
	
	@PostMapping
	public ResponseEntity<ConsultaResponseDto> insert(@Valid @RequestBody ConsultaCadastroDto dados) {
		Consulta consulta = service.insert(dados);
		ConsultaResponseDto consultaResponse = new ConsultaResponseDto(consulta);
		return ResponseEntity.ok(consultaResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ConsultaCancelamentoResponseDto> delete(@PathVariable Long id, @Valid @RequestBody CancelaConsultaDto dados) {
		ConsultaCancelamentoResponseDto consultaCancelada = service.cancelarAgendamento(id, dados);		
		return ResponseEntity.ok(consultaCancelada);
	}
}
