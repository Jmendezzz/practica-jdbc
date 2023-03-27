package org.example;
import org.example.entities.Producto;
import org.example.repository.impl.ProductoRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        ProductoRepositoryImpl productoRepository = new ProductoRepositoryImpl();

        /* Testing Save Query

        Producto producto = new Producto();
        producto.setNombre("Mouse");
        producto.setPrecio(50000.0);
        producto.setFechaRegistro(LocalDate.now());
        productoRepository.save(producto);

        */

        //Testing Update Query

        Producto productToUpdate = productoRepository.getById(3L);
        productToUpdate.setNombre("Cambio de nombre Update");
        productoRepository.update(productToUpdate);

        //Testing Delete Query

        Producto productToDelete = productoRepository.getById(5L);
        productoRepository.deleteById(productToDelete.getId());


    }
}
