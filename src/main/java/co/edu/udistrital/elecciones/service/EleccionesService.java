package co.edu.udistrital.elecciones.service;

import co.edu.udistrital.elecciones.model.*;
import co.edu.udistrital.elecciones.repository.VotacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EleccionesService {
    @Autowired private VotacionRepository repo;
    
    public List<CandidatoDTO> getTarjeton() {
        return repo.getCandidatos();
    }
    
    public String registrarVoto(int numero) {
        if (numero < 1 || numero > 6) 
            return "Voto inválido (1-6)";
        repo.registrarVoto(numero);
        return "Voto registrado para candidato " + numero;
    }
    
    public List<CandidatoDTO> getVotosPorCandidato() {
        return repo.getCandidatosConVotos();
    }
    
    public EstadisticaDTO getEstadisticas() {
        return repo.getEstadisticas();
    }
}
