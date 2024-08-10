package sonda.med.app.model.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import sonda.med.app.model.entities.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long>{

	Page<Medico> findAllByAtivoTrue(Pageable paginacao);
}
