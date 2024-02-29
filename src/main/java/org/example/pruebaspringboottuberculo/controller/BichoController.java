package org.example.pruebaspringboottuberculo.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.pruebaspringboottuberculo.model.Bicho;
import org.example.pruebaspringboottuberculo.repository.BichoRepository;
import org.example.pruebaspringboottuberculo.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/bicho")
public class BichoController {

    @Autowired
    private BichoRepository bichoRepository;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/{id}")
    public Bicho getBichobyId(@PathVariable Integer id) {
        return bichoRepository.getBichoById(id);
    }

    @GetMapping("/observacionesByNombre/{nombre}")
    public String getObservacionesByNombre(@PathVariable String nombre) {
        return bichoRepository.getObservacionesByNombre(nombre);
    }

    @GetMapping("/nombreBichoByBichoIdAndTuberculoId/{id}/{tuberculoid}")
    public String getNombreBichoByBichoIdAndTuberculoId(@PathVariable Integer id, @PathVariable Integer tuberculoid) {
        return bichoRepository.getNombreByBichoIdAndTuberculoId(id, tuberculoid);
    }

    /*
    * Recuerda que, la primera posición de cualquier tipo de Array/Lista es 0. */
    @GetMapping("/nombreBichoByTuberculoId/{tuberculoid}")
    public List<String> getNombreBichoByTuberculoId(@PathVariable Integer tuberculoid, @RequestParam Integer posicion) {
        List<String> nombreBichos = bichoRepository.getNombreBichoByTuberculoId(tuberculoid);
        return Collections.singletonList(nombreBichos.get(posicion));
    }

    @GetMapping("/todoBichos")
    public List<Bicho> getAll() {
        return bichoRepository.findAll();
    }

    @PostMapping("/BichoPost/post")
    public ResponseEntity<Bicho> newBicho(@RequestBody Bicho bicho, @RequestParam String token) {
        if (securityService.tokenDeValidacion(token)) {
            return new ResponseEntity<Bicho>(bichoRepository.save(bicho), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    //No hace falta que pongas en el cuerpo del put el tuberculo con tuberculoid
    @PutMapping("/BichoPut/put/{id}")
    public ResponseEntity<Bicho> updateBicho(@PathVariable Integer id, @RequestBody Bicho nuevoBicho, @RequestParam String token) {
        if (!securityService.tokenDeValidacion(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            Bicho bicho = new Bicho();
            var bichoOpcional = bichoRepository.findById(id);
            if (bichoOpcional.isEmpty()) {
                bicho = nuevoBicho;
            } else {
                bicho = bichoOpcional.get();
                bicho.setNombre(nuevoBicho.getNombre());
                bicho.setDescripcion(nuevoBicho.getDescripcion());
            }
            return new ResponseEntity<Bicho>(bichoRepository.save(bicho), HttpStatus.OK);
        }
    }

    @DeleteMapping("/BichoDelete/delete/{id}")
    public ResponseEntity<Bicho> deleteBicho(@PathVariable Integer id, @RequestParam String token) {
        ResponseEntity<Bicho> responseEntity = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if (securityService.tokenDeValidacion(token)) {
            Bicho salida = new Bicho();
            if (bichoRepository.existsById(id)) {
                salida = bichoRepository.findById(id).get();
                bichoRepository.deleteById(id);
                responseEntity = new ResponseEntity<>(salida, HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<Bicho>(salida, HttpStatus.NOT_FOUND);
            }
        }
        return responseEntity;
    }

}
