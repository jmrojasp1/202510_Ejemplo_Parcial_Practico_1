package co.edu.uniandes.dse.parcialprueba.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.ConsultaMedicaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PacienteService {

    @Autowired
	PacienteRepository pacienteRepository;

    @Autowired
	ConsultaMedicaRepository consultaMedicaRepository;

	@Transactional
	public PacienteEntity createPaciente(PacienteEntity pacienteEntity) throws EntityNotFoundException, IllegalOperationException {
		log.info("Inicia proceso de creación del paciente");
		
		if (pacienteEntity.getNombre() == null)
			throw new IllegalOperationException("Nombre is not valid");

        Optional<ConsultaMedicaEntity> consultaMedicaEntity = consultaMedicaRepository.findById(pacienteEntity.getConsulta_medica().getId());
        if (consultaMedicaEntity.isEmpty())
            throw new IllegalOperationException("Consulta Medica is not valid");
		
		if (pacienteEntity.getEdad() == null)
			throw new IllegalOperationException("Edad is not valid");

        if (pacienteEntity.getCelular() == null)
			throw new IllegalOperationException("Celular is not valid");
		
		if (pacienteEntity.getCorreo() == null)
			throw new IllegalOperationException("Correo is not valid");
        
        
        pacienteEntity.setConsulta_medica(consultaMedicaEntity.get());
		log.info("Termina proceso de creación del paciente");
		return pacienteRepository.save(pacienteEntity);
	}
}