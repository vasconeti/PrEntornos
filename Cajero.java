/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vasconeti.repaso_entornos2;

import java.util.ArrayList;

/**
 * Representa un cajero de supermercado que gestiona el cobro de productos
 * y lleva el registro de los tickets emitidos durante el día.
 *
 * @author loren
 */
public class Cajero {

    /** Porcentaje de IVA aplicado a todas las ventas. */
    private static final double IVA = 0.21;

    /** Nombre del cajero. */
    private String nombre;

    /** Número de tickets emitidos durante el día. */
    private int ticketsEmitidos;

    /** Suma total facturada durante el día (IVA incluido). */
    private double totalDia;

    /** Lista de productos del ticket actual. */
    private ArrayList<Producto> productos;

    /**
     * Crea un nuevo cajero con el nombre indicado.
     * El contador de tickets y el total del día se inicializan a cero.
     *
     * @param nombre nombre del cajero
     */
    public Cajero(String nombre) {
        this.nombre = nombre;
        this.ticketsEmitidos = 0;
        this.totalDia = 0;
        this.productos = new ArrayList<>();
    }

    /**
     * Añade un producto al ticket actual.
     *
     * @param producto producto que se va a añadir
     */
    public void anadirProducto(Producto producto) {
        productos.add(producto);
    }

    /**
     * Elimina un producto del ticket actual.
     *
     * @param producto producto que se va a eliminar
     */
    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
    }

    /**
     * Cobra el ticket actual: calcula importes, imprime el ticket por pantalla,
     * actualiza el contador de tickets y el total del día, y vacía la lista
     * de productos para el siguiente cliente.
     */
    public void cobrar() {
        double subtotal = calcularSubtotal();
        double importeIva = subtotal * IVA;
        double total = subtotal + importeIva;

        System.out.println("===== TICKET =====");
        System.out.println("Cajero: " + nombre);
        imprimirLineasProductos();
        System.out.println("------------------");
        imprimirResumenTicket(subtotal, importeIva, total);
        System.out.println("==================");

        ticketsEmitidos = ticketsEmitidos + 1;
        totalDia = totalDia + total;
        productos.clear();
    }

    /**
     * Calcula la suma de los importes de todos los productos del ticket actual,
     * sin incluir el IVA.
     *
     * @return subtotal del ticket sin IVA
     */
    private double calcularSubtotal() {
        double subtotal = 0;
        for (Producto producto : productos) {
            subtotal = subtotal + producto.calcularImporte();
        }
        return subtotal;
    }

    /**
     * Imprime por pantalla una línea por cada producto del ticket actual,
     * mostrando nombre, cantidad e importe.
     */
    private void imprimirLineasProductos() {
        for (Producto producto : productos) {
            System.out.println(producto.getNombre() + " x" + producto.getCantidad()
                    + " = " + String.format("%.2f", producto.calcularImporte()) + " EUR");
        }
    }

    /**
     * Imprime por pantalla las líneas de subtotal, IVA y total del ticket.
     *
     * @param subtotal   importe sin IVA
     * @param importeIva importe del IVA
     * @param total      importe total con IVA
     */
    private void imprimirResumenTicket(double subtotal, double importeIva, double total) {
        System.out.println("Subtotal: " + String.format("%.2f", subtotal) + " EUR");
        System.out.println("IVA (21%): " + String.format("%.2f", importeIva) + " EUR");
        System.out.println("TOTAL: " + String.format("%.2f", total) + " EUR");
    }

    /**
     * Imprime por pantalla el resumen del cierre de caja: número de tickets
     * emitidos, total facturado e IVA recaudado durante el día.
     */
    public void cierreCaja() {
        double ivaRecaudado = totalDia - (totalDia / (1 + IVA));

        System.out.println("===== CIERRE DE CAJA =====");
        System.out.println("Cajero: " + nombre);
        System.out.println("--------------------------");
        System.out.println("Tickets emitidos: " + ticketsEmitidos);
        System.out.println("Total facturado:  " + String.format("%.2f", totalDia) + " EUR");
        System.out.println("IVA recaudado:    " + String.format("%.2f", ivaRecaudado) + " EUR");
        System.out.println("==========================");
    }

    /**
     * Indica si el ticket actual no tiene ningún producto.
     *
     * @return {@code true} si el ticket está vacío, {@code false} en caso contrario
     */
    public boolean ticketVacio() {
        return productos.isEmpty();
    }

    /**
     * Devuelve el número de tickets emitidos durante el día.
     *
     * @return número de tickets emitidos
     */
    public int getTicketsEmitidos() {
        return ticketsEmitidos;
    }

    /**
     * Devuelve el total facturado durante el día, IVA incluido.
     *
     * @return total facturado en euros
     */
    public double getTotalDia() {
        return totalDia;
    }
}
