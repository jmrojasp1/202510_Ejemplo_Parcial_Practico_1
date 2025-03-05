package co.edu.uniandes.dse.parcialprueba.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class ConsultaMedicaEntity extends BaseEntity {

	@PodamExclude
    @OneToMany(mappedBy = "consultaMedica", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<PacienteEntity> pacientes = new ArrayList<>();
    
    @PodamExclude
    @ManyToOne
	private String causa;    
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
}