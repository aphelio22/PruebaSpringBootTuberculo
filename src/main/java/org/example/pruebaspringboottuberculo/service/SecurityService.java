package org.example.pruebaspringboottuberculo.service;

import org.example.pruebaspringboottuberculo.model.Bicho;
import org.example.pruebaspringboottuberculo.repository.BichoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    private BichoRepository bichoRepository;
    public Boolean tokenDeValidacion(Integer id, String token) {
        return bichoRepository.findByIdAndToken(id, token)!=null;
    }

    public Boolean tokenDeCreacion(String token) {
        return token.equals("t0k3nCr3aci0n");
    }
}
