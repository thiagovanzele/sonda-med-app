package sonda.med.app.model.repositories;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import sonda.med.app.model.entities.Medico;
import sonda.med.app.model.enums.Especialidade;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

	Page<Medico> findAllByAtivoTrue(Pageable paginacao);

	@Query("""
			select m from Medico m
			where
			m.ativo = true
			and
			m.especialidade = :especialidade
			and
			m.id not in(
			    select c.medico.id from Consulta c
			    where
			    c.data = :dataConsulta
			)
			order by random()
			limit 1
						""")
	public Medico escolherMedicoAleatorio(Especialidade especialidade, @NotNull @Future LocalDateTime dataConsulta);
	
	
	@Query("""
			select m.ativo
			from Medico m
			where
			m.id = :idMedico
			""")
	Boolean findAtivoById(Long idMedico);
	
	
}
