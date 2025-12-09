package co.edu.udistrital.elecciones.model;

import java.time.LocalDateTime;
import java.util.List;

public class EstadisticaDTO {
    private List<CandidatoDTO> candidatos;
    private int totalVotos;
    private LocalDateTime fechaConsulta;

    public EstadisticaDTO() {
    }

    public List<CandidatoDTO> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(List<CandidatoDTO> candidatos) {
        this.candidatos = candidatos;
    }

    public int getTotalVotos() {
        return totalVotos;
    }

    public void setTotalVotos(int totalVotos) {
        this.totalVotos = totalVotos;
    }

    public LocalDateTime getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(LocalDateTime fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }
}
