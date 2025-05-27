package com.cebem.medidor.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.cebem.medidor.models.Superhero;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SuperheroService {

    // Simulamos una "base de datos" en memoria
    private final List<Superhero> lista = new ArrayList<>();

    public List<Superhero> obtenerTodos() {
        return new ArrayList<>(lista); // Retornamos una copia para evitar modificaciones externas
    }

    public Optional<Superhero> obtenerPorAlias(String alias) {
        return lista.stream()
                .filter(s -> s.getAlias().equalsIgnoreCase(alias))
                .findFirst();
    }

    public Superhero crear(Superhero nuevo) {
        lista.add(nuevo);
        return nuevo;
    }

    public Optional<Superhero> actualizar(String alias, Superhero actualizado) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getAlias().equalsIgnoreCase(alias)) {
                lista.set(i, actualizado);
                return Optional.of(actualizado);
            }
        }
        return Optional.empty();
    }

    public boolean eliminar(String alias) {
        return lista.removeIf(s -> s.getAlias().equalsIgnoreCase(alias));
    }
}