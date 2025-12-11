package co.edu.udistrital.elecciones.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.udistrital.elecciones.model.CandidatoDTO;
import co.edu.udistrital.elecciones.model.EstadisticaDTO;

@Repository
public class VotacionRepository {

    private final List<CandidatoDTO> candidatos = new ArrayList<>();
    private int votosBlanco = 0;

    public void agregarCandidato(CandidatoDTO candidato) {
        candidatos.add(candidato);
    }

    // Tarjetón ordenado + voto en blanco al final
    public List<CandidatoDTO> getTarjeton() {
        List<CandidatoDTO> lista = new ArrayList<>(candidatos);

        lista.add(new CandidatoDTO(
                lista.size() + 1,
                "VOTO EN BLANCO",
                "N/A",
                votosBlanco
        ));

        return lista;
    }

    public void registrarVoto(int numero) {
        if (numero == candidatos.size() + 1) {
            votosBlanco++;
        } else if (numero >= 1 && numero <= candidatos.size()) {
            CandidatoDTO c = candidatos.get(numero - 1);
            c.setVotos(c.getVotos() + 1);
        }
    }

    public List<CandidatoDTO> getCandidatosConVotos() {
        return getTarjeton();
    }

    public EstadisticaDTO getEstadisticas() {
        int total = votosBlanco;
        for (CandidatoDTO c : candidatos) total += c.getVotos();

        EstadisticaDTO stats = new EstadisticaDTO();
        stats.setCandidatos(getTarjeton());
        stats.setTotalVotos(total);
        stats.setFechaConsulta(LocalDateTime.now());
        return stats;
    }
}
