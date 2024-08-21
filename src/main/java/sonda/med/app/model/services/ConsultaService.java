package sonda.med.app.model.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.ValidationException;
import sonda.med.app.model.entities.Consulta;
import sonda.med.app.model.entities.Medico;
import sonda.med.app.model.entities.Paciente;
import sonda.med.app.model.entities.dto.request.CancelaConsultaDto;
import sonda.med.app.model.entities.dto.request.ConsultaCadastroDto;
import sonda.med.app.model.entities.dto.response.ConsultaCancelamentoResponseDto;
import sonda.med.app.model.exceptions.ResourceNotFoundException;
import sonda.med.app.model.repositories.ConsultaRepository;
import sonda.med.app.model.repositories.MedicoRepository;
import sonda.med.app.model.repositories.PacienteRepository;
import sonda.med.app.model.services.validacoes.ValidadorAgendamentoDeConsulta;

@Service
public class ConsultaService {

	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	List<ValidadorAgendamentoDeConsulta> validadores;
	
	public Consulta insert(ConsultaCadastroDto dados) {

		validadores.forEach(v -> v.validarAgendamento(dados));
		
		Paciente paciente = pacienteRepository.findById(dados.pacienteId()).orElseThrow(() -> new ResourceNotFoundException(dados.pacienteId(), Paciente.class));
		Medico medico = selecionaMedico(dados);
		Consulta consulta = new Consulta(paciente, medico, dados.dataConsulta());
		return consultaRepository.save(consulta);
	}

	private Medico selecionaMedico(ConsultaCadastroDto dados) {
		if (dados.medicoId() != null) {
			return medicoRepository.findById(dados.medicoId()).orElseThrow(() -> new ResourceNotFoundException(dados.medicoId(), Medico.class));
		}
		
		if (dados.especialidade() == null) {
			throw new ValidationException("Especialidade deve ser informada");
		}
		
		Medico medico = medicoRepository.escolherMedicoAleatorio(dados.especialidade(), dados.dataConsulta());
		
		if (medico == null) {
			throw new ValidationException("Não existe médico disponível para a data");
		}
		
		return medico;
	}
	
	public ConsultaCancelamentoResponseDto cancelarAgendamento(Long id, CancelaConsultaDto dados) {
		Consulta consulta = consultaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, Consulta.class));
		long duracao = java.time.Duration.between(LocalDateTime.now(), consulta.getData()).toHours();
		
		if (duracao <= 24) {
			throw new ValidationException("O cancelamento deve ser feito com pelo menos 24horas de antecedencia");
		}
		
		ConsultaCancelamentoResponseDto consultaCancelada = new ConsultaCancelamentoResponseDto(dados, consulta);
		
		deletaConsulta(consulta);
		
		return consultaCancelada;
	}
	
	private void deletaConsulta(Consulta consulta) {
		consultaRepository.delete(consulta);
	}
}
