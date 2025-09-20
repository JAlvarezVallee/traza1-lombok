package com.ejercicio.traza1.model;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Sucursal {
    @EqualsAndHashCode.Include @ToString.Include
    private Long id;

    @EqualsAndHashCode.Include @ToString.Include
    private String nombre;

    private Domicilio domicilio;
}

