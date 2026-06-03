package org.example;

import java.util.Scanner;

public class InventarioCine {

    public static void main(String[] args) {

        // Se crea Scanner para leer datos ingresados por teclado.
        Scanner sc = new Scanner(System.in);

        // Datos quemados para validar el ingreso al sistema.
        String usuarioCorrecto = "admin";
        String contrasenaCorrecta = "1234";

        // Variables para validar usuario y contraseña.
        String usuario;
        String contrasena;
        int intentos = 0;
        boolean accesoPermitido = false;

        // Inventario inicial de asientos disponibles.
        int asientosDisponibles = 20;

        // Precio fijo del ticket definido por el ejercicio.
        int precioBase = 10000;

        // Variable para controlar el menú principal del sistema.
        int opcionSistema = 0;

        // Validación de usuario y contraseña con máximo 3 intentos.
        while (intentos < 3 && !accesoPermitido) {

            System.out.println();
            System.out.println("===== ACCESO AL SISTEMA CINEMAX =====");

            System.out.print("Ingrese usuario: ");
            usuario = sc.nextLine();

            System.out.print("Ingrese contraseña: ");
            contrasena = sc.nextLine();

            if (usuario.equals(usuarioCorrecto) && contrasena.equals(contrasenaCorrecta)) {
                accesoPermitido = true;
                System.out.println();
                System.out.println("Acceso permitido. Bienvenido al sistema CineMax.");
            } else {
                intentos++;
                System.out.println();
                System.out.println("Usuario o contraseña incorrectos. Intento " + intentos + " de 3.");
            }
        }

        // Si se superan los 3 intentos, el programa termina.
        if (!accesoPermitido) {
            System.out.println();
            System.out.println("Acceso bloqueado. Superó el número máximo de intentos.");
            sc.close();
            return;
        }

        // Ciclo principal: el sistema funciona mientras haya asientos y el usuario no elija salir.
        while (asientosDisponibles > 0 && opcionSistema != 2) {

            System.out.println();
            System.out.println("===== SISTEMA DE COMPRA CINEMAX =====");
            System.out.println("Asientos disponibles: " + asientosDisponibles);
            System.out.println("Valor fijo del ticket: $10000");
            System.out.println();
            System.out.println("1. Comprar tickets");
            System.out.println("2. Salir");
            System.out.println();

            System.out.print("Seleccione una opción: ");

            // Validar que la opción del sistema sea numérica.
            while (!sc.hasNextInt()) {
                System.out.println();
                System.out.println("Error: debe ingresar un número.");
                sc.next();
                System.out.print("Seleccione una opción: ");
            }

            opcionSistema = sc.nextInt();
            sc.nextLine();

            if (opcionSistema == 1) {

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

                // Variable de control para validar el nombre.
                boolean nombreValido = false;

                // Validar que el nombre no esté vacío y no contenga números.
                do {
                    System.out.println();
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
                        System.out.println();
                        System.out.println("Error: el nombre debe ser texto y no puede contener números.");
                    }

                } while (!nombreValido);

                // Variable de control para validar la opción de película.
                boolean opcionPeliculaValida = false;

                // Mostrar el menú de películas hasta que el usuario seleccione una opción válida.
                do {
                    System.out.println();
                    System.out.println("===== MENÚ DE PELÍCULAS =====");
                    System.out.println();
                    System.out.println("1. Avatar 3 (Clasificación PG 10 años)");
                    System.out.println("2. El Conjuro (Clasificación R 18 años)");
                    System.out.println("3. Toy Story 4 (Clasificación G todas las edades)");
                    System.out.println("4. Una Batalla Tras Otra (Clasificación R 18 años)");
                    System.out.println("5. Los Pecadores (Clasificación R 18 años)");
                    System.out.println();

                    System.out.print("Selecciona una película del 1 al 5: ");

                    // Validar que la opción sea numérica antes de leerla.
                    while (!sc.hasNextInt()) {
                        System.out.println();
                        System.out.println("Error: debes ingresar un número válido.");
                        sc.next();
                        System.out.print("Selecciona una película del 1 al 5: ");
                    }

                    opcionPelicula = sc.nextInt();
                    sc.nextLine();

                    // Selector múltiple: asigna datos según la película elegida.
                    switch (opcionPelicula) {

                        case 1:
                            tituloPelicula = "Avatar 3";
                            clasificacionEdad = "PG";
                            edadMinima = 10;
                            opcionPeliculaValida = true;
                            break;

                        case 2:
                            tituloPelicula = "El Conjuro";
                            clasificacionEdad = "R";
                            edadMinima = 18;
                            opcionPeliculaValida = true;
                            break;

                        case 3:
                            tituloPelicula = "Toy Story 4";
                            clasificacionEdad = "G";
                            edadMinima = 0;
                            opcionPeliculaValida = true;
                            break;

                        case 4:
                            tituloPelicula = "Una Batalla Tras Otra";
                            clasificacionEdad = "R";
                            edadMinima = 18;
                            opcionPeliculaValida = true;
                            break;

                        case 5:
                            tituloPelicula = "Los Pecadores";
                            clasificacionEdad = "R";
                            edadMinima = 18;
                            opcionPeliculaValida = true;
                            break;

                        default:
                            System.out.println();
                            System.out.println("Opción inválida. Intenta nuevamente.");
                            opcionPeliculaValida = false;
                            break;
                    }

                } while (!opcionPeliculaValida);

                // Validar que la cantidad de tickets sea numérica.
                System.out.println();
                System.out.print("Ingresa la cantidad de tickets a comprar: ");

                while (!sc.hasNextInt()) {
                    System.out.println();
                    System.out.println("Error: la cantidad de tickets debe ser un número.");
                    sc.next();
                    System.out.print("Ingresa la cantidad de tickets a comprar: ");
                }

                cantidadTickets = sc.nextInt();

                // Validar que la cantidad sea mayor que cero y que no supere los asientos disponibles.
                while (cantidadTickets <= 0 || cantidadTickets > asientosDisponibles) {

                    System.out.println();

                    if (cantidadTickets <= 0) {
                        System.out.println("Error: la cantidad de tickets debe ser mayor que cero.");
                    } else {
                        System.out.println("Error: no hay suficientes asientos disponibles.");
                        System.out.println("Asientos disponibles: " + asientosDisponibles);
                    }

                    System.out.println();
                    System.out.print("Ingresa la cantidad de tickets a comprar: ");

                    while (!sc.hasNextInt()) {
                        System.out.println();
                        System.out.println("Error: la cantidad de tickets debe ser un número.");
                        sc.next();
                        System.out.print("Ingresa la cantidad de tickets a comprar: ");
                    }

                    cantidadTickets = sc.nextInt();
                }

                // Validar que la edad sea numérica.
                System.out.println();
                System.out.print("Ingresa la edad del comprador: ");

                while (!sc.hasNextInt()) {
                    System.out.println();
                    System.out.println("Error: la edad debe ser un número.");
                    sc.next();
                    System.out.print("Ingresa la edad del comprador: ");
                }

                edad = sc.nextInt();
                sc.nextLine();

                // Validar que la edad no sea negativa.
                while (edad < 0) {
                    System.out.println();
                    System.out.println("Error: la edad no puede ser negativa.");
                    System.out.println();
                    System.out.print("Ingresa la edad del comprador: ");

                    while (!sc.hasNextInt()) {
                        System.out.println();
                        System.out.println("Error: la edad debe ser un número.");
                        sc.next();
                        System.out.print("Ingresa la edad del comprador: ");
                    }

                    edad = sc.nextInt();
                    sc.nextLine();
                }

                // Se llaman métodos estáticos para reutilizar la lógica de cálculo.
                subtotal = calcularSubtotal(cantidadTickets, precioBase);
                descuento = calcularDescuento(subtotal);
                precioTotalPagado = calcularTotal(subtotal, descuento);

                // Validar edad mínima según clasificación de la película.
                if (edad >= edadMinima) {

                    mensajeCompra = nombre + ", su compra ha sido aprobada.";

                    // Actualizar inventario restando los tickets vendidos.
                    asientosDisponibles = asientosDisponibles - cantidadTickets;

                } else {
                    mensajeCompra = nombre + ", su compra ha sido rechazada: no cumple la edad mínima de " + edadMinima + " años.";
                }

                // Mostrar resumen de compra.
                System.out.println();
                System.out.println("===== RESUMEN DE COMPRA =====");
                System.out.println("Cliente: " + nombre);
                System.out.println("Película: " + tituloPelicula);
                System.out.println("Clasificación: " + clasificacionEdad);
                System.out.println("Cantidad de tickets: " + cantidadTickets);
                System.out.println("Subtotal: " + subtotal);
                System.out.println("Descuento: " + descuento);
                System.out.println("Total final: " + precioTotalPagado);
                System.out.println(mensajeCompra);
                System.out.println("Asientos disponibles después de la operación: " + asientosDisponibles);

                // Ciclo for: imprime un ticket simulado por cada entrada comprada.
                if (edad >= edadMinima) {

                    System.out.println();
                    System.out.println("===== TICKETS GENERADOS =====");

                    for (int i = 1; i <= cantidadTickets; i++) {
                        System.out.println();
                        System.out.println("Ticket #" + i);
                        System.out.println("Cliente: " + nombre);
                        System.out.println("Película: " + tituloPelicula);
                        System.out.println("Precio unitario: " + precioBase);
                        System.out.println("-----------------------------");
                    }

                    System.out.println();
                    System.out.println("Gracias por su compra. Lo invitamos a visitar nuestra deliciosa cafetería.");
                } else {
                    System.out.println();
                    System.out.println("Consulte en nuestra cartelera las películas recomendadas para su edad.");
                }

            } else if (opcionSistema == 2) {

                System.out.println();
                System.out.println("Gracias por usar el sistema CineMax.");

            } else {

                System.out.println();
                System.out.println("Opción inválida. Intenta nuevamente.");
            }
        }

        // Mensaje final si la función se queda sin asientos.
        if (asientosDisponibles == 0) {
            System.out.println();
            System.out.println("No hay más asientos disponibles. Función agotada.");
        }

        // Se cierra Scanner para liberar el recurso.
        sc.close();
    }

    // Método estático para calcular el subtotal.
    public static int calcularSubtotal(int cantidadTickets, int precioBase) {
        return cantidadTickets * precioBase;
    }

    // Método estático para calcular el descuento.
    public static double calcularDescuento(int subtotal) {

        if (subtotal > 50000) {
            return subtotal * 0.10;
        } else {
            return 0;
        }
    }

    // Método estático para calcular el total final.
    public static double calcularTotal(int subtotal, double descuento) {
        return subtotal - descuento;
    }
}