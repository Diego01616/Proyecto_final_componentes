package co.edu.udistrital.elecciones.controller;

import co.edu.udistrital.elecciones.model.*;
import co.edu.udistrital.elecciones.service.EleccionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/elecciones")
@CrossOrigin(origins = "*")
public class EleccionesController {
    
    @Autowired private EleccionesService service;

    @GetMapping("/tarjeton")
    public ResponseEntity<List<CandidatoDTO>> getTarjeton() {
        return ResponseEntity.ok(service.getTarjeton());
    }

    @PostMapping("/votar")
    public ResponseEntity<String> votar(@RequestBody VotoDTO voto) {
        String resultado = service.registrarVoto(voto.getNumero());
        return ResponseEntity.ok(resultado);
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
        return ResponseEntity.ok("Diego Muñoz - Universidad Distrital");
    }
}
