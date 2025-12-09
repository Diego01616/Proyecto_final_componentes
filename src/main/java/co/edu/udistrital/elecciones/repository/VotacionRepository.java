package co.edu.udistrital.elecciones.repository;

import co.edu.udistrital.elecciones.model.*;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class VotacionRepository {
    private static List<CandidatoDTO> candidatos = new ArrayList<>();
    private static int votosBlanco = 0;
    
    static {
        candidatos.add(new CandidatoDTO(1, "Cien Años de Soledad", "Partido Macondo", 0));
        candidatos.add(new CandidatoDTO(2, "El Amor en los Tiempos del Cólera", "Partido Caribe", 0));
        candidatos.add(new CandidatoDTO(3, "Crónica de una Muerte Anunciada", "Partido Realismo", 0));
        candidatos.add(new CandidatoDTO(4, "El Otoño del Patriarca", "Partido Dictadura", 0));
        candidatos.add(new CandidatoDTO(5, "La Hoja de Mar", "Partido Poesía", 0));
    }
    
    public List<CandidatoDTO> getCandidatos() {
        List<CandidatoDTO> tarjeton = new ArrayList<>();
        for (CandidatoDTO c : candidatos) {
            tarjeton.add(new CandidatoDTO(c.getNumero(), c.getNombreLibro(), c.getPartido(), c.getVotos()));
        }
        tarjeton.add(new CandidatoDTO(6, "VOTO EN BLANCO", "N/A", votosBlanco));
        return tarjeton;
    }
    
    public void registrarVoto(int numero) {
        if (numero == 6) votosBlanco++;
        else if (numero >= 1 && numero <= 5) {
            candidatos.get(numero-1).setVotos(candidatos.get(numero-1).getVotos() + 1);
        }
    }
    
    public List<CandidatoDTO> getCandidatosConVotos() {
        return getCandidatos();
    }
    
    public EstadisticaDTO getEstadisticas() {
        int total = votosBlanco;
        for (CandidatoDTO c : candidatos) total += c.getVotos();
        
        EstadisticaDTO stats = new EstadisticaDTO();
        stats.setCandidatos(getCandidatos());
        stats.setTotalVotos(total);
        stats.setFechaConsulta(LocalDateTime.now());
        return stats;
    }
}
