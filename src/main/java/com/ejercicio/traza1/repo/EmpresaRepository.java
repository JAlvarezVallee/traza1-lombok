package com.ejercicio.traza1.repo;

import com.ejercicio.traza1.model.Empresa;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class EmpresaRepository {
    private final Map<Long, Empresa> data = new HashMap<>();
    private final AtomicLong seq = new AtomicLong(0);

    public Empresa save(Empresa e) {
        if (e.getId() == null) e.setId(seq.incrementAndGet());
        data.put(e.getId(), e);
        return e;
    }

    public Optional<Empresa> findById(Long id) {
        return Optional.ofNullable(data.get(id));
    }

    public List<Empresa> findAll() {
        return new ArrayList<>(data.values());
    }

    public List<Empresa> findByNombre(String nombre) {
        String n = nombre == null ? "" : nombre.trim().toLowerCase();
        return data.values().stream()
                .filter(e -> e.getNombre() != null && e.getNombre().toLowerCase().contains(n))
                .collect(Collectors.toList());
    }

    public Optional<Empresa> updateCuil(Long id, String nuevoCuil) {
        Empresa e = data.get(id);
        if (e == null) return Optional.empty();
        e.setCuil(nuevoCuil);
        return Optional.of(e);
    }

    public Optional<Empresa> deleteById(Long id) {
        return Optional.ofNullable(data.remove(id));
    }
}

