package sonda.med.app.entitie.medico;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import sonda.med.app.model.entities.Consulta;
import sonda.med.app.model.entities.Endereco;
import sonda.med.app.model.entities.Medico;
import sonda.med.app.model.entities.Paciente;
import sonda.med.app.model.enums.Especialidade;
import sonda.med.app.model.repositories.MedicoRepository;

@ActiveProfiles("test")
@DataJpaTest
public class MedicoRepositoryTest {

	@Autowired
	private MedicoRepository repository;

	@Autowired
	private TestEntityManager em;

	private static final Endereco end = new Endereco("09061030", "Casimiro de abreu", null, "Vila aquilino",
			"Santo Andre", "sp", "244");

	@Test
	@DisplayName("Deveria devolver quando unico medico cadastrado nao esta disponivel na data")
	void escolherMedicoAleatorioCenario1() {
		em.persist(end);
		var proximaSegundaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

		var medico = cadastrarMedico("Medico", "medico@sonda.med", "11999999999", "123456", "CARDIOLOGIA", end);
		var paciente = cadastrarPaciente("Paciente", "paciente@teste.com", "11222222222", "42477414895", end);
		cadastrarConsulta(medico, paciente, proximaSegundaAs10);

		var medicoLivre = repository.escolherMedicoAleatorio(Especialidade.CARDIOLOGIA, proximaSegundaAs10);
		assertThat(medicoLivre).isNull();
	}

	@Test
	@DisplayName("Deveria devolver medico quando ele estiver disponivel na data")
	void escolherMedicoAleatorioCenario2() {
		em.persist(end);
		var proximaSegundaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

		var medico = cadastrarMedico("Medico", "medico@sonda.med", "11999999999", "123456", "CARDIOLOGIA", end);

		var medicoLivre = repository.escolherMedicoAleatorio(Especialidade.CARDIOLOGIA, proximaSegundaAs10);
		assertThat(medicoLivre).isEqualTo(medico);
	}

	private Consulta cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
		return em.persist(new Consulta(paciente, medico, data));
	}

	private Medico cadastrarMedico(String nome, String email, String telefone, String crm, String especialidade,
			Endereco endereco) {
		Medico medico = new Medico();
		medico.setAtivo(true);
		medico.setCrm(crm);
		medico.setEmail(email);
		medico.setNome(nome);
		medico.setTelefone(telefone);
		medico.setEspecialidade(Especialidade.valueOf(especialidade.toUpperCase()));
		medico.setEndereco(endereco);
		em.persist(medico);
		return medico;
	}

	private Paciente cadastrarPaciente(String nome, String email, String telefone, String cpf, Endereco endereco) {
		var paciente = new Paciente(nome, email, telefone, cpf, end);
		em.persist(paciente);
		return paciente;
	}

}
