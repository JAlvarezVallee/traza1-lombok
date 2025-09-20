package com.ejercicio.traza1.model;

import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Pais {
    @EqualsAndHashCode.Include @ToString.Include
    private Long id;

    @EqualsAndHashCode.Include @ToString.Include
    private String nombre;

    @Builder.Default
    private Set<Provincia> provincias = new HashSet<>();

    // Helper para mantener la relación
    public void addProvincia(Provincia p) {
        if (p == null) return;
        provincias.add(p);
        p.setPais(this);
    }
}
