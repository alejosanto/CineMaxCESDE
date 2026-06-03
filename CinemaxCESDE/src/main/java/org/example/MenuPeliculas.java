package org.example;

import java.util.Scanner;

public class MenuPeliculas {

    public static void main(String[] args) {

        // Se crea Scanner para leer datos ingresados por teclado.
        Scanner sc = new Scanner(System.in);

        // Variables de entrada.
        String nombre;
        int opcionPelicula;
        int cantidadTickets;
        int edad;

        // Variables relacionadas con la película seleccionada.
        String tituloPelicula = "";
        String clasificacionEdad = "";
        int edadMinima = 0;

        // Variables de cálculo.
        int subtotal;
        double descuento;
        double precioTotalPagado;

        // Variable de salida.
        String mensajeCompra;

        // Datos quemados: valores fijos definidos dentro del programa.
        int precioBase = 10000;

        // Variable de control para validar el nombre.
        boolean nombreValido = false;

        // Validar que el nombre no esté vacío y no contenga números.
        do {
            System.out.print("Ingresa tu nombre y apellidos: ");
            nombre = sc.nextLine();

            nombreValido = true;

            if (nombre.trim().isEmpty()) {
                nombreValido = false;
            }

            for (int i = 0; i < nombre.length(); i++) {
                if (Character.isDigit(nombre.charAt(i))) {
                    nombreValido = false;
                }
            }

            if (!nombreValido) {
                System.out.println("Error: el nombre debe ser texto y no puede contener números.");
            }

        } while (!nombreValido);

        // Variable de control para validar la opción del menú.
        boolean opcionValida = false;

        // Mostrar el menú hasta que el usuario seleccione una opción válida.
        do {
            System.out.println("===== MENÚ DE PELÍCULAS =====");
            System.out.println("1. Avatar 3 (Clasificación PG 10 años) ");
            System.out.println("2. El Conjuro (Clasificación R 18 años) ");
            System.out.println("3. Toy Story 4 (Clasificación G todas las edades) ");
            System.out.println("4. Una Batalla Tras Otra (Clasificación R 18 años): ");
            System.out.println("5. Los Pecadores (Clasificación R 18 años): ");

            System.out.print("Selecciona una película del 1 al 5: ");

            // Validar que la opción sea numérica antes de leerla.
            while (!sc.hasNextInt()) {
                System.out.println("Error: debes ingresar un número válido.");
                sc.next();
                System.out.print("Selecciona una película del 1 al 5: ");
            }

            opcionPelicula = sc.nextInt();

            // Selector múltiple: asigna datos según la película elegida.
            switch (opcionPelicula) {

                case 1:
                    tituloPelicula = "Avatar 3";
                    clasificacionEdad = "PG";
                    edadMinima = 10;
                    opcionValida = true;
                    break;

                case 2:
                    tituloPelicula = "El Conjuro";
                    clasificacionEdad = "R";
                    edadMinima = 18;
                    opcionValida = true;
                    break;

                case 3:
                    tituloPelicula = "Toy Story 4";
                    clasificacionEdad = "G";
                    edadMinima = 0;
                    opcionValida = true;
                    break;

                case 4:
                    tituloPelicula = "Una Batalla Tras Otra";
                    clasificacionEdad = "R";
                    edadMinima = 18;
                    opcionValida = true;
                    break;

                case 5:
                    tituloPelicula = "Los Pecadores";
                    clasificacionEdad = "R";
                    edadMinima = 18;
                    opcionValida = true;
                    break;

                default:
                    System.out.println("Opción inválida. Intenta nuevamente.");
                    opcionValida = false;
                    break;
            }

        } while (!opcionValida);

        // Validar que la cantidad de tickets sea numérica.
        System.out.print("Ingresa la cantidad de tickets a comprar: ");

        while (!sc.hasNextInt()) {
            System.out.println("Error: la cantidad de tickets debe ser un número.");
            sc.next();
            System.out.print("Ingresa la cantidad de tickets a comprar: ");
        }

        cantidadTickets = sc.nextInt();

        // Validar que la cantidad de tickets sea mayor que cero.
        while (cantidadTickets <= 0) {
            System.out.println("Error: la cantidad de tickets debe ser mayor que cero.");
            System.out.print("Ingresa la cantidad de tickets a comprar: ");

            while (!sc.hasNextInt()) {
                System.out.println("Error: la cantidad de tickets debe ser un número.");
                sc.next();
                System.out.print("Ingresa la cantidad de tickets a comprar: ");
            }

            cantidadTickets = sc.nextInt();
        }

        // Validar que la edad sea numérica.
        System.out.print("Ingresa la edad del comprador: ");

        while (!sc.hasNextInt()) {
            System.out.println("Error: la edad debe ser un número.");
            sc.next();
            System.out.print("Ingresa la edad del comprador: ");
        }

        edad = sc.nextInt();

        // Validar que la edad no sea negativa.
        while (edad < 0) {
            System.out.println("Error: la edad no puede ser negativa.");
            System.out.print("Ingresa la edad del comprador: ");

            while (!sc.hasNextInt()) {
                System.out.println("Error: la edad debe ser un número.");
                sc.next();
                System.out.print("Ingresa la edad del comprador: ");
            }

            edad = sc.nextInt();
        }

        // Calcular subtotal multiplicando cantidad de tickets por precio base.
        subtotal = cantidadTickets * precioBase;

        // Aplicar descuento del 10% si el subtotal supera $50000.
        if (subtotal > 50000) {
            descuento = subtotal * 0.10;
        } else {
            descuento = 0;
        }

        // Calcular total final.
        precioTotalPagado = subtotal - descuento;

        // Validar edad mínima según clasificación de la película.
        if (edad >= edadMinima) {
            mensajeCompra = nombre + ", su compra ha sido aprobada.";
        } else {
            mensajeCompra = nombre + ", su compra ha sido rechazada: no cumple la edad mínima de " + edadMinima + " años.";
        }

        // Mostrar resumen de compra.
        System.out.println("===== RESUMEN DE COMPRA =====");
        System.out.println("Cliente: " + nombre);
        System.out.println("Película: " + tituloPelicula);
        System.out.println("Clasificación: " + clasificacionEdad);
        System.out.println("Cantidad de tickets: " + cantidadTickets);
        System.out.println("Subtotal: " + subtotal);
        System.out.println("Descuento: " + descuento);
        System.out.println("Total final: " + precioTotalPagado);
        System.out.println(mensajeCompra);

        // Ciclo for: imprime un ticket simulado por cada entrada comprada.
        if (edad >= edadMinima) {

            System.out.println("===== TICKETS GENERADOS =====");

            for (int i = 1; i <= cantidadTickets; i++) {
                System.out.println("Ticket #" + i);
                System.out.println("Cliente: " + nombre);
                System.out.println("Película: " + tituloPelicula);
                System.out.println("Precio unitario: " + precioBase);
                System.out.println("-----------------------------");
            }

            System.out.println("Gracias por su compra. Lo invitamos a visitar nuestra deliciosa cafetería.");
        } else {
            System.out.println("Consulte en nuestra cartelera las películas recomendadas para su edad.");
        }

        // Se cierra Scanner para liberar el recurso.
        sc.close();
    }
}