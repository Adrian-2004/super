package com.cebem.medidor.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.List;

import jakarta.persistence.Entity;

@Data // Genera getters, setters, toString, equals y hashCode
@NoArgsConstructor // Constructor sin argumentos
@AllArgsConstructor // Constructor con todos los argumentos
@Builder // Patrón builder para crear instancias más fácilmente
// @Entity
public class Superhero {
    private String nombreReal;
    private String alias;
    private List<String> poderes;
    private String afiliacion; // Por ejemplo: "Liga de la Justicia", "Vengadores"

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Poder {
        private String nombre;
        private String descripcion;
        private int nivel; // 1 a 100, por ejemplo
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Afiliacion {
        private String nombre;
        private String descripcion;
        private List<Superhero> miembros; // Lista de superhéroes que pertenecen a esta afiliación
    }
}
