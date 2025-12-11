package co.edu.udistrital.elecciones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.udistrital.elecciones.model.CandidatoDTO;
import co.edu.udistrital.elecciones.model.EstadisticaDTO;
import co.edu.udistrital.elecciones.repository.VotacionRepository;

@Service
public class EleccionesService {

    @Autowired
    private VotacionRepository repo;

    public List<CandidatoDTO> getTarjeton() {
        return repo.getTarjeton();
    }

    public String registrarVoto(int numero) {
        repo.registrarVoto(numero);
        return "Voto registrado correctamente";
    }

    public List<CandidatoDTO> getVotosPorCandidato() {
        return repo.getCandidatosConVotos();
    }

    public EstadisticaDTO getEstadisticas() {
        return repo.getEstadisticas();
    }

    public void registrarCandidato(CandidatoDTO candidato) {
        candidato.setNumero(repo.getTarjeton().size());  
        candidato.setVotos(0);
        repo.agregarCandidato(candidato);
    }
}
