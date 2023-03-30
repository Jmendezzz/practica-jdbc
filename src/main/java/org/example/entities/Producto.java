package org.example.entities;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Producto {
    private  Long id;
    private String nombre;
    private Double precio;
    private LocalDate fechaRegistro;
    private Categoria categoria;


}
