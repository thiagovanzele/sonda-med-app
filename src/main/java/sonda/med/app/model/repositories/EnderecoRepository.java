package sonda.med.app.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sonda.med.app.model.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
