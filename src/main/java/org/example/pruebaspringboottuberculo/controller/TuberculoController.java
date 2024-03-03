package org.example.pruebaspringboottuberculo.controller;

import org.example.pruebaspringboottuberculo.repository.TuberculoRepository;
import org.example.pruebaspringboottuberculo.service.SecurityService;
import org.example.pruebaspringboottuberculo.model.Tuberculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tuberculo")
public class TuberculoController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private TuberculoRepository tuberculoRepository;

    @GetMapping("/todoTuberculo")
    public List<Tuberculo> getAll() {
        return tuberculoRepository.findAll();
    }

    @DeleteMapping("/deleteTuberculo/{id}")
    public ResponseEntity<Tuberculo> deleteTuberculoById(@PathVariable Integer id, @RequestParam String token) {
        ResponseEntity<Tuberculo> responseEntity = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if (securityService.tokenDeValidacion(id, token)){
            Tuberculo salida = new Tuberculo();
            if (tuberculoRepository.existsById(id)) {
                salida = tuberculoRepository.findById(id).get();
                tuberculoRepository.deleteById(id);
                responseEntity = new ResponseEntity<Tuberculo>(salida, HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<Tuberculo>(salida, HttpStatus.NOT_FOUND);
            }
        }
        return responseEntity;
    }
}
