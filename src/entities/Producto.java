/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Archivo: projectdatosstream/entities/Producto.java
package entities;

import enums.Categoria;
import java.util.Objects;

public class Producto {
    private final String nombre;
    private final double precio;
    private final Categoria categoria;

    public Producto(String nombre, double precio, Categoria categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    // Getters
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public Categoria getCategoria() { return categoria; }

     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(nombre.toLowerCase(), producto.nombre.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase());
    }

    @Override
    public String toString() {
        return String.format("%s - $%.2f - %s", nombre, precio, categoria);
    }
}
