package co.edu.udistrital.elecciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.udistrital.elecciones.model.CandidatoDTO;
import co.edu.udistrital.elecciones.model.EstadisticaDTO;
import co.edu.udistrital.elecciones.model.VotoDTO;
import co.edu.udistrital.elecciones.service.EleccionesService;

@RestController
@RequestMapping("/api/elecciones")
@CrossOrigin(origins = "*")
public class EleccionesController {

    @Autowired
    private EleccionesService service;

    @GetMapping("/tarjeton")
    public ResponseEntity<List<CandidatoDTO>> getTarjeton() {
        return ResponseEntity.ok(service.getTarjeton());
    }

    @PostMapping("/votar")
    public ResponseEntity<String> votar(@RequestBody VotoDTO voto) {
        return ResponseEntity.ok(service.registrarVoto(voto.getNumero()));
    }

    @GetMapping("/votos")
    public ResponseEntity<List<CandidatoDTO>> getVotos() {
        return ResponseEntity.ok(service.getVotosPorCandidato());
    }

    @GetMapping("/estadisticas")
    public ResponseEntity<EstadisticaDTO> getEstadisticas() {
        return ResponseEntity.ok(service.getEstadisticas());
    }

    @GetMapping("/autor")
    public ResponseEntity<String> getAutor() {
        return ResponseEntity.ok("Juan David - Diego Bautista - Geronimo Alarcón - Universidad Distrital");
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody CandidatoDTO candidato) {
        service.registrarCandidato(candidato);
        return ResponseEntity.ok("Candidato registrado correctamente");
    }
}
