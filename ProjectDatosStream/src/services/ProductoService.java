/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Producto;
import enums.Categoria;
import java.util.*;
import java.util.stream.Collectors;

public class ProductoService {
    private final Set<Producto> productos = new HashSet<>();
    
    public void agregarProducto(Producto producto) {
        if (existeProducto(producto.getNombre())) {
            throw new IllegalArgumentException("¡El producto '" + producto.getNombre() + "' ya existe!");
        }
        productos.add(producto);
    }
    public boolean existeProducto(String nombre) {
        return productos.stream()
                .anyMatch(p -> p.getNombre().equalsIgnoreCase(nombre.trim()));
    }

    public List<Producto> obtenerProductosCaros() {
        return productos.stream()
                .filter(p -> p.getPrecio() > 100)
                .collect(Collectors.toList());
    }

    public List<String> obtenerNombresProductos() {
        return productos.stream()
                .map(Producto::getNombre)
                .collect(Collectors.toList());
    }

    public double calcularPrecioTotal() {
        return productos.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();
    }

    public Map<Categoria, List<Producto>> agruparPorCategoria() {
    return productos.stream()
            .collect(Collectors.groupingBy(Producto::getCategoria));
}
    public Optional<Producto> encontrarProductoMasCaro() {
    return productos.stream()
            .max(Comparator.comparingDouble(Producto::getPrecio));
}

    public long contarProductosBaratos() {
        return productos.stream()
                .filter(p -> p.getPrecio() < 80)
                .count();
    }
    
    public Set<Producto> getTodosProductos() {
        return new HashSet<>(productos);
    }
}
