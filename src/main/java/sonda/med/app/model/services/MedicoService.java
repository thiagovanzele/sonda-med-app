package sonda.med.app.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sonda.med.app.model.entities.Endereco;
import sonda.med.app.model.entities.Medico;
import sonda.med.app.model.entities.dto.request.MedicoCadastroDto;
import sonda.med.app.model.entities.dto.response.MedicoResponseDto;
import sonda.med.app.model.entities.dto.update.MedicoUpdateDto;
import sonda.med.app.model.enums.Especialidade;
import sonda.med.app.model.exceptions.ResourceNotFoundException;
import sonda.med.app.model.repositories.MedicoRepository;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private EnderecoService enderecoService;

	@Transactional
	public Medico insert(MedicoCadastroDto medicoDto) {
		Endereco endereco = enderecoService.insert(medicoDto.cep(), medicoDto.numero());

		Medico medico = new Medico();
		medico.setNome(medicoDto.nome());
		medico.setCrm(medicoDto.crm());
		medico.setEmail(medicoDto.email());
		medico.setTelefone(medicoDto.telefone());
		medico.setEndereco(endereco);
		medico.setEspecialidade(Especialidade.valueOf(medicoDto.especialidade().toUpperCase()));
		medico.setAtivo(true);

		return medicoRepository.save(medico);
	}

	public Page<MedicoResponseDto> findAll(Pageable paginacao) {
		return medicoRepository.findAllByAtivoTrue(paginacao).map(MedicoResponseDto::new);
	}

	public MedicoResponseDto findById(Long id) {
		Medico medico = medicoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id, Medico.class));
		return new MedicoResponseDto(medico);
	}
	
	@Transactional
	public void delete(Long id) {
		Medico medico = medicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, Medico.class));
		inativarMedico(medico);
	}

	@Transactional
	public Medico update(Long id, MedicoUpdateDto medicoUpdateDto) {
		Medico medico = medicoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id, Medico.class));
		updateData(medico, medicoUpdateDto);
		return medicoRepository.save(medico);
	}

	private void updateData(Medico medico, MedicoUpdateDto medicoUpdateDto) {
		if (medicoUpdateDto.nome() != null && !medicoUpdateDto.nome().isBlank()) {
			medico.setNome(medicoUpdateDto.nome());
		}
		
		if (medicoUpdateDto.telefone() != null && !medicoUpdateDto.telefone().isBlank()) {
			medico.setTelefone(medicoUpdateDto.telefone());
		}

		if (medicoUpdateDto.cep() != null && !medicoUpdateDto.cep().isBlank()) {
			Endereco end = enderecoService.insert(medicoUpdateDto.cep(), medicoUpdateDto.numero());
			medico.setEndereco(end);
		}
	}
	
	public void inativarMedico(Medico medico) {
		medico.setAtivo(false);
	}

}
