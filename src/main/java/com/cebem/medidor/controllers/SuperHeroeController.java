package com.cebem.medidor.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cebem.medidor.models.Superhero;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/superheroes")
@RequiredArgsConstructor // Inyecta dependencias final automáticamente (no necesario si no usas servicio aún)
public class SuperHeroeController {

    // Para ejemplo simple, usamos lista en memoria (en una app real usarías un servicio o repositorio)
    private final List<Superhero> lista = new ArrayList<>();

    @GetMapping
    public List<Superhero> obtenerTodos() {
        return lista;
    }

    @GetMapping("/{alias}")
    public ResponseEntity<Superhero> obtenerPorAlias(@PathVariable String alias) {
        Optional<Superhero> resultado = lista.stream()
                .filter(s -> s.getAlias().equalsIgnoreCase(alias))
                .findFirst();

        return resultado.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Superhero> crearSuperHeroe(@RequestBody Superhero nuevo) {
        lista.add(nuevo);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{alias}")
    public ResponseEntity<Superhero> actualizarSuperHeroe(@PathVariable String alias, @RequestBody Superhero actualizado) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getAlias().equalsIgnoreCase(alias)) {
                lista.set(i, actualizado);
                return ResponseEntity.ok(actualizado);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{alias}")
    public ResponseEntity<Void> eliminarSuperHeroe(@PathVariable String alias) {
        boolean eliminado = lista.removeIf(s -> s.getAlias().equalsIgnoreCase(alias));
        return eliminado ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}