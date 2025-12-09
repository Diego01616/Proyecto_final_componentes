package co.edu.udistrital.elecciones.model;

public class CandidatoDTO {
    private int numero;
    private String nombreLibro;
    private String partido;
    private int votos;

    public CandidatoDTO(int numero, String nombreLibro, String partido, int votos) {
        this.numero = numero;
        this.nombreLibro = nombreLibro;
        this.partido = partido;
        this.votos = votos;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }
}
