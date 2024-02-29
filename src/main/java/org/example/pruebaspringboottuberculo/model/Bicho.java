package org.example.pruebaspringboottuberculo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "bichos")
public class Bicho implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;

    @ManyToOne
    //Esto es importante:
    /*
    * Tienes que especificar, por alguna razón,
    * el nombre de la columna en la Base de Datos
    * de la relación '@ManyToOne', porque aunque esté bien
    * en el atributo Hibernate no lo pilla. */
    @JoinColumn(name = "tuberculoid")
    private Tuberculo tuberculoid;
}
