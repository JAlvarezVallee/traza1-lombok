package com.ejercicio.traza1.model;

import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Empresa {
    @EqualsAndHashCode.Include @ToString.Include
    private Long id;

    @EqualsAndHashCode.Include @ToString.Include
    private String nombre;

    @ToString.Include
    private String cuil;

    @Builder.Default
    private Set<Sucursal> sucursales = new HashSet<>();

    public void addSucursal(Sucursal s) {
        if (s == null) return;
        sucursales.add(s);
    }
}

