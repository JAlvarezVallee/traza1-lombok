package com.ejercicio.traza1.model;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Domicilio {
    @EqualsAndHashCode.Include @ToString.Include
    private Long id;

    @EqualsAndHashCode.Include @ToString.Include
    private String calle;

    @EqualsAndHashCode.Include @ToString.Include
    private String numero;

    // No lo incluimos en toString para evitar cadenas largas
    private Localidad localidad;
}
