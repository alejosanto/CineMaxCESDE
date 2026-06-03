package org.example;

import java.util.Scanner;

public class CalculoCompra {

    public static void main(String[] args) {

        // Se crea un objeto Scanner para leer datos ingresados por teclado.
        Scanner sc = new Scanner(System.in);

        // Ciclo 1

        // Variables de entrada: .
        String nombre;
        int cantidadTickets;
        int edad;

        // Variables de cálculo:
        int edadMinima = 0;
        int subtotal;
        double descuento;
        double precioTotalPagado;

        // Variable de salida:
        String mensajeCompra;

        // Datos quemados: valores fijos definidos dentro del programa.
        int precioBase = 10000;
        String clasificacionEdad = "R";

        // Entrada de datos: se solicita información al comprador.
        System.out.print("Ingresa tu nombre y apellidos: ");
        nombre = sc.nextLine();

        System.out.print("Ingresa la cantidad de tickets a comprar: ");
        cantidadTickets = sc.nextInt();

        System.out.print("Ingresa la edad del comprador: ");
        edad = sc.nextInt();

        // Se calcula el subtotal multiplicando cantidad por precio base.
        subtotal = cantidadTickets * precioBase;

        // Condicional simple: si el subtotal supera 50000, se aplica descuento del 10%.
        if (subtotal > 50000) {
            descuento = subtotal * 0.10;
        } else {
            descuento = 0;
        }

        // Se calcula el total final restando el descuento al subtotal.
        precioTotalPagado = subtotal - descuento;

        // Condicional múltiple con if/else: define la edad mínima según la clasificación.
        if (clasificacionEdad.equals("G")) {
            edadMinima = 0;
        } else if (clasificacionEdad.equals("PG")) {
            edadMinima = 10;
        } else if (clasificacionEdad.equals("PG-13")) {
            edadMinima = 13;
        } else if (clasificacionEdad.equals("R")) {
            edadMinima = 18;
        }

        // Validación de edad: determina si el comprador puede realizar la compra.
        if (edad >= edadMinima) {
            mensajeCompra = nombre + ", Su compra ha sido aprobada";
        } else {
            mensajeCompra = nombre + ", Su compra ha sido rechazada: No cumple la edad mínima de " + edadMinima + " años";
        }

        // Salida de datos: se muestran los resultados calculados.
        System.out.println("Subtotal: " + subtotal);
        System.out.println("Descuento: " + descuento);
        System.out.println("Total final: " + precioTotalPagado);
        System.out.println(mensajeCompra);

        // Mensaje adicional según el resultado de la compra.
        if (mensajeCompra.contains("aprobada")) {
            System.out.println("Gracias por su compra. Lo invitamos a visitar nuestra deliciosa cafetería. ¡Bienvenido!");
        } else {
            System.out.println("Gracias por visitarnos. Consulte en nuestra cartelera las películas recomendadas para su edad.");
        }

        // Se cierra Scanner para liberar el recurso usado para leer datos.
        sc.close();
    }
}
