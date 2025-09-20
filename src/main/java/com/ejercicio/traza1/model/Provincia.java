package com.ejercicio.traza1.model;

import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Provincia {
    @EqualsAndHashCode.Include @ToString.Include
    private Long id;

    @EqualsAndHashCode.Include @ToString.Include
    private String nombre;

    // backref simple (no lo incluimos en toString para evitar recursión)
    private Pais pais;

    @Builder.Default
    private Set<Localidad> localidades = new HashSet<>();

    public void addLocalidad(Localidad l) {
        if (l == null) return;
        localidades.add(l);
        l.setProvincia(this);
    }
}

