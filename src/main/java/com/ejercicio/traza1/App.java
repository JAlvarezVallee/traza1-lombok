package com.ejercicio.traza1;

import com.ejercicio.traza1.model.*;
import com.ejercicio.traza1.repo.EmpresaRepository;

public class App {
    public static void main(String[] args) {
        // Repositorio de empresas
        EmpresaRepository repo = new EmpresaRepository();

        // === 1) País ===
        Pais ar = Pais.builder().id(1L).nombre("Argentina").build();

        // === 2) Provincias ===
        Provincia bsas = Provincia.builder().id(1L).nombre("Buenos Aires").build();
        ar.addProvincia(bsas);

        Provincia cba = Provincia.builder().id(2L).nombre("Córdoba").build();
        ar.addProvincia(cba);

        // === 3) Localidades y sus relaciones ===
        // Buenos Aires: CABA y La Plata
        Localidad caba = Localidad.builder().id(1L).nombre("CABA").build();
        bsas.addLocalidad(caba);

        Localidad laPlata = Localidad.builder().id(2L).nombre("La Plata").build();
        bsas.addLocalidad(laPlata);

        // Córdoba: Córdoba Capital y Villa Carlos Paz
        Localidad cbaCapital = Localidad.builder().id(3L).nombre("Córdoba Capital").build();
        cba.addLocalidad(cbaCapital);

        Localidad vcp = Localidad.builder().id(4L).nombre("Villa Carlos Paz").build();
        cba.addLocalidad(vcp);

        // === 4) Domicilios por localidad ===
        Domicilio domCaba = Domicilio.builder().id(1L).calle("Av. Siempreviva").numero("742").build();
        caba.addDomicilio(domCaba);

        Domicilio domLaPlata = Domicilio.builder().id(2L).calle("Calle 50").numero("1234").build();
        laPlata.addDomicilio(domLaPlata);

        Domicilio domCbaCapital = Domicilio.builder().id(3L).calle("Bv. San Juan").numero("800").build();
        cbaCapital.addDomicilio(domCbaCapital);

        Domicilio domVcp = Domicilio.builder().id(4L).calle("Av. San Martín").numero("1000").build();
        vcp.addDomicilio(domVcp);

        // === 5) Sucursales (1: CABA, 2: La Plata, 3: Córdoba Capital, 4: Villa Carlos Paz) ===
        Sucursal suc1 = Sucursal.builder().id(1L).nombre("Sucursal 1").domicilio(domCaba).build();
        Sucursal suc2 = Sucursal.builder().id(2L).nombre("Sucursal 2").domicilio(domLaPlata).build();
        Sucursal suc3 = Sucursal.builder().id(3L).nombre("Sucursal 3").domicilio(domCbaCapital).build();
        Sucursal suc4 = Sucursal.builder().id(4L).nombre("Sucursal 4").domicilio(domVcp).build();

        // === 6) Empresas y relación con sucursales ===
        Empresa emp1 = Empresa.builder().nombre("Empresa 1").cuil("30-11111111-9").build();
        emp1.addSucursal(suc1);
        emp1.addSucursal(suc2);
        repo.save(emp1); // id auto-asignado por el repositorio

        Empresa emp2 = Empresa.builder().nombre("Empresa 2").cuil("30-22222222-7").build();
        emp2.addSucursal(suc3);
        emp2.addSucursal(suc4);
        repo.save(emp2);

        // === 7) Operaciones pedidas ===
        System.out.println("\n-- Mostrar todas las empresas --");
        repo.findAll().forEach(App::printEmpresa);

        System.out.println("\n-- Buscar empresa por ID (1) --");
        repo.findById(1L).ifPresentOrElse(
                App::printEmpresa,
                () -> System.out.println("No existe empresa con id 1")
        );

        System.out.println("\n-- Buscar empresas por nombre que contenga 'Empresa' --");
        repo.findByNombre("Empresa").forEach(App::printEmpresa);

        System.out.println("\n-- Actualizar CUIL de empresa id=1 --");
        repo.updateCuil(1L, "30-99999999-0").ifPresent(App::printEmpresa);

        System.out.println("\n-- Eliminar empresa id=2 --");
        repo.deleteById(2L).ifPresent(e -> System.out.println("Eliminada: " + e.getNombre()));

        System.out.println("\n-- Listado final --");
        repo.findAll().forEach(App::printEmpresa);
    }

    private static void printEmpresa(Empresa e) {
        System.out.println("Empresa{id=" + e.getId() + ", nombre=" + e.getNombre() + ", cuil=" + e.getCuil() + "}");
        if (e.getSucursales() != null) {
            e.getSucursales().forEach(s ->
                    System.out.println("   - " + s.getNombre() + " @ " +
                            s.getDomicilio().getCalle() + " " + s.getDomicilio().getNumero() + " - " +
                            (s.getDomicilio().getLocalidad() != null ? s.getDomicilio().getLocalidad().getNombre() : "")
                    )
            );
        }
    }
}
