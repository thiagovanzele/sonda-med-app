package sonda.med.app.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sonda.med.app.model.entities.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

	

}
