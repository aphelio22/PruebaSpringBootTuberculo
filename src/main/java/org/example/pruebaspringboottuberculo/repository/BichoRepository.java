package org.example.pruebaspringboottuberculo.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.pruebaspringboottuberculo.model.Bicho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BichoRepository extends JpaRepository<Bicho, Integer> {
    public Bicho getBichoById(Integer id);

    @Query("select b.descripcion from Bicho b where b.nombre = :nombre")
    public String getObservacionesByNombre(String nombre);

    @Query("select b.nombre from Bicho b where b.id = :id and b.tuberculoid.id = :tuberculoid")
    public String getNombreByBichoIdAndTuberculoId(Integer id, Integer tuberculoid);

    //Es importante que sepas que para acceder a los campos de tubérculo uses el atributo que relaciona ambos modelos.
    @Query("select b.nombre from Bicho b where b.tuberculoid.id = :tuberculoid")
    public List<String> getNombreBichoByTuberculoId(Integer tuberculoid);
}
