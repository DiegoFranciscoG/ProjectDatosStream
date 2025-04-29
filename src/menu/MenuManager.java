/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import entities.Producto;
import enums.Categoria;
import javax.swing.*;
import services.ProductoService;
import utils.InputHelper;
import utils.MessageHelper;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MenuManager {
    private final ProductoService productoService = new ProductoService();
    private JDialog mainDialog;
    private JPanel mainPanel;
    
    public void iniciar() {
        crearInterfaz();
        configurarEventos();
        mostrarVentana();
    }

    private void crearInterfaz() {
        mainDialog = new JDialog();
        mainDialog.setTitle("Sistema de Productos");
        mainDialog.setModal(true);
        mainDialog.setResizable(false);
        
        mainPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setPreferredSize(new Dimension(800, 500));

        String[] opciones = {
            "Agregar producto",
            "Mostrar productos caros (>$100)",
            "Mostrar nombres de productos",
            "Calcular precio total",
            "Agrupar por categoría",
            "Encontrar producto más caro",
            "Contar productos baratos (<$80)",
            "Salir"
        };

        for (String opcion : opciones) {
            JButton btn = new JButton(opcion);
            btn.setFont(new Font("Arial", Font.PLAIN, 18));
            btn.setFocusPainted(false);
            mainPanel.add(btn);
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        mainDialog.add(scrollPane);
        mainDialog.pack();
        mainDialog.setLocationRelativeTo(null);
    }

    private void configurarEventos() {
        Component[] componentes = mainPanel.getComponents();
        for (int i = 0; i < componentes.length; i++) {
            if (componentes[i] instanceof JButton) {
                JButton btn = (JButton) componentes[i];
                final int opcion = i;
                btn.addActionListener(e -> manejarOpcion(opcion));
            }
        }
    }

    private void mostrarVentana() {
        mainDialog.setVisible(true);
    }

    private void manejarOpcion(int opcion) {
        try {
            switch (opcion) {
                case 0 -> agregarProducto();
                case 1 -> mostrarProductosCaros();
                case 2 -> mostrarNombresProductos();
                case 3 -> mostrarPrecioTotal();
                case 4 -> mostrarAgrupadoPorCategoria();
                case 5 -> mostrarProductoMasCaro();
                case 6 -> mostrarContarProductosBaratos();
                case 7 -> salir();
                default -> {}
            }
        } catch (Exception e) {
            MessageHelper.mostrarError(e.getMessage());
        }
    }

    // Métodos implementados
     private void agregarProducto() {
        try {
            String nombre = InputHelper.obtenerNombre();
            double precio = InputHelper.obtenerPrecio();
            Categoria categoria = InputHelper.obtenerCategoria();
            
            Producto producto = new Producto(nombre, precio, categoria);
            
            if (productoService.existeProducto(nombre)) {
                MessageHelper.mostrarErrorDuplicado(nombre);
                return;
            }
            
            productoService.agregarProducto(producto);
            MessageHelper.mostrarInfo("Producto agregado exitosamente!");
            
        } catch (IllegalArgumentException e) {
            MessageHelper.mostrarError(e.getMessage());
        }
    }

    private void mostrarProductosCaros() {
        List<Producto> productos = productoService.obtenerProductosCaros();
        MessageHelper.mostrarProductos(productos, "Productos caros (precio > $100)");
    }

    private void mostrarNombresProductos() {
        List<String> nombres = productoService.obtenerNombresProductos();
        if (nombres.isEmpty()) {
            MessageHelper.mostrarInfo("No hay productos registrados");
            return;
        }
        MessageHelper.mostrarInfo("Nombres de productos:\n" + String.join("\n", nombres));
    }

    private void mostrarPrecioTotal() {
        double total = productoService.calcularPrecioTotal();
        MessageHelper.mostrarInfo(String.format("Precio total: $%.2f", total));
    }

    private void mostrarAgrupadoPorCategoria() {
    Map<Categoria, List<Producto>> agrupados = productoService.agruparPorCategoria();
    MessageHelper.mostrarAgrupacion(agrupados);
}

   private void mostrarProductoMasCaro() {
    Optional<Producto> masCaro = productoService.encontrarProductoMasCaro();
    masCaro.ifPresentOrElse(
        producto -> MessageHelper.mostrarInfo("Producto más caro:\n" + producto),
        () -> MessageHelper.mostrarInfo("No hay productos registrados")
    );
}

    private void mostrarContarProductosBaratos() {
        long cantidad = productoService.contarProductosBaratos();
        MessageHelper.mostrarInfo("Productos baratos: " + cantidad);
    }
    
    private void salir() {
        mainDialog.dispose();
        MessageHelper.mostrarInfo("¡Gracias por usar el sistema!");
        System.exit(0);
    }
}