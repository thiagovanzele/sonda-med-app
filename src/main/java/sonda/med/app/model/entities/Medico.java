package sonda.med.app.model.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sonda.med.app.model.enums.Especialidade;

@Entity
@Table(name = "tb_medico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medico implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String email;
	
	private String telefone;
	
	private String Crm;
	
	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;
	
	@ManyToOne()
	private Endereco endereco;

}
