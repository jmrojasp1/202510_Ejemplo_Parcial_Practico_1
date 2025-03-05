package co.edu.uniandes.dse.parcialprueba.services;


import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.ConsultaMedicaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConsultaMedicaService {

    @Autowired
	PacienteRepository pacienteRepository;

    @Autowired
	ConsultaMedicaRepository consultaMedicaRepository;



	@Transactional
	public ConsultaMedicaEntity createConsulta(ConsultaMedicaEntity consultaMedicaEntity) throws EntityNotFoundException, IllegalOperationException {
		log.info("Inicia proceso de creación de la Consulta Medica");
		
		if (consultaMedicaEntity.getCausa() == null)
			throw new IllegalOperationException("Causa is not valid");
        if(consultaMedicaEntity.getFecha().after(new Date())==false)
            throw new IllegalOperationException("Date is not valid");
		
        
		log.info("Termina proceso de creación de la Consulta Medica");
		return consultaMedicaRepository.save(consultaMedicaEntity);
	}
}