package com.ejercicio.traza1.model;

import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Localidad {
    @EqualsAndHashCode.Include @ToString.Include
    private Long id;

    @EqualsAndHashCode.Include @ToString.Include
    private String nombre;

    private Provincia provincia;

    @Builder.Default
    private Set<Domicilio> domicilios = new HashSet<>();

    public void addDomicilio(Domicilio d) {
        if (d == null) return;
        domicilios.add(d);
        d.setLocalidad(this);
    }
}
