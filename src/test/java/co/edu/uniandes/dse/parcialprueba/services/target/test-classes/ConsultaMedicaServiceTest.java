package co.edu.uniandes.dse.parcialprueba.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.services.ConsultaMedicaService;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
@DataJpaTest
@Transactional
@Import(ConsultaMedicaService.class)
class ConsultaMedicaServiceTest {

	@Autowired
	private ConsultaMedicaService consultaMedicaService;

	@Autowired
	private TestEntityManager entityManager;

	private PodamFactory factory = new PodamFactoryImpl();

	private List<AuthorEntity> authorList = new ArrayList<>();

	@BeforeEach
	void setUp() {
		clearData();
		insertData();
	}

	private void clearData() {
		entityManager.getEntityManager().createQuery("delete from ConsultaMedicaEntity").executeUpdate();
		entityManager.getEntityManager().createQuery("delete from PacienteEntity").executeUpdate();
		
	}

	private void insertData() {
		for (int i = 0; i < 3; i++) {
			ConsultaMedicaEntity ponsultaMedicaEntity = factory.manufacturePojo(ConsultaMedicaEntity.class);
			entityManager.persist(consultaMedicaEntity);
			authorList.add(consultaMedicaEntity);
		}
	}
@Test
void testCreateConsulta() throws IllegalOperationException {
ConsultaMedicaEntity newEntity = factory.manufacturePojo(ConsultaMedicaEntity.class);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); 
		calendar.add(Calendar.DATE, -15);
		newEntity.setDate(calendar.getTime());
		ConsultaMedicaEntity result = consultaMedicaService.createConsulta(newEntity);
		assertNotNull(result);

		ConsultaMedicaEntity entity = entityManager.find(ConsultaMedicaEntity.class, result.getId());

		assertEquals(newEntity.getId(), entity.getId());
		assertEquals(newEntity.getName(), entity.getName());
}
}