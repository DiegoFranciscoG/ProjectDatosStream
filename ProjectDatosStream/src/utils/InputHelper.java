/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;
import javax.swing.*;
import enums.Categoria;

public class InputHelper {
    public static String obtenerNombre() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        return nombre.trim();
    }

    public static double obtenerPrecio() {
        try {
            String precioStr = JOptionPane.showInputDialog("Ingrese el precio del producto:");
            if (precioStr == null) throw new Exception("Operación cancelada");
            return Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Precio debe ser un número válido");
        } catch (Exception e) {
            throw new RuntimeException("Operación cancelada por el usuario");
        }
    }

    public static Categoria obtenerCategoria() {
        Categoria[] categorias = Categoria.values();
        Object seleccion = JOptionPane.showInputDialog(
                null,
                "Seleccione categoría:",
                "Categoría",
                JOptionPane.QUESTION_MESSAGE,
                null,
                categorias,
                categorias[0]
        );
        
        if (seleccion == null) {
            throw new IllegalArgumentException("Categoría no seleccionada");
        }
        return (Categoria) seleccion;
    }
}