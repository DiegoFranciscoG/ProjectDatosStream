/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import javax.swing.*;
import entities.Producto;
import enums.Categoria;
import java.util.List;
import java.util.Map;

public class MessageHelper {
    public static void mostrarErrorDuplicado(String nombreProducto) {
        String[] lineas = {
    "Error de duplicado",
    "",
    "El producto '" + nombreProducto + "' ya existe con estas características:",
    "Mismo nombre exacto",
    "",
    "No se permiten productos duplicados."
};
        JOptionPane.showMessageDialog(null, lineas, "Error de duplicado", JOptionPane.ERROR_MESSAGE);
    }
    public static void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void mostrarInfo(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mostrarProductos(List<Producto> productos, String titulo) {
        if (productos.isEmpty()) {
            mostrarInfo("No hay productos para mostrar");
            return;
        }
        
        StringBuilder sb = new StringBuilder(titulo + ":\n");
        productos.forEach(p -> sb.append("• ").append(p).append("\n"));
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public static void mostrarAgrupacion(Map<Categoria, List<Producto>> agrupados) {
        if (agrupados.isEmpty()) {
            mostrarInfo("No hay productos registrados");
            return;
        }
        
        StringBuilder sb = new StringBuilder("Productos por categoría:\n");
        agrupados.forEach((categoria, lista) -> {
            sb.append("\n").append(categoria).append(":\n");
            lista.forEach(p -> sb.append(" - ").append(p).append("\n"));
        });
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}