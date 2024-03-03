package org.example.pruebaspringboottuberculo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import org.example.pruebaspringboottuberculo.enums.Estado;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tuberculo")
public class Tuberculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private Date fecha_caducidad;
    private String estado;
    private String observaciones;
    private String token;

    @OneToMany(mappedBy = "tuberculoid", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Bicho> bichos = new ArrayList<Bicho>();
}
