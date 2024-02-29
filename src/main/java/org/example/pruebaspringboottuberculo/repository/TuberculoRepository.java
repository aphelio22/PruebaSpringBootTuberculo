package org.example.pruebaspringboottuberculo.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.pruebaspringboottuberculo.model.Tuberculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TuberculoRepository extends JpaRepository<Tuberculo, Integer> {
}
