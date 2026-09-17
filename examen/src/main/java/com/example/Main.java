package com.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero(scanner, "Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    registrarUsuario(scanner);
                    break;
                case 2:
                    registrarFactura(scanner);
                    break;
                case 3:
                    consultarUsuarios();
                    break;
                case 4:
                    consultarFacturas();
                    break;
                case 5:
                    calcularImpuesto(scanner);
                    break;
                case 6:
                    calcularTotal(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
            System.out.println();
        } while (opcion != 0);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("===== MENU =====");
        System.out.println("1. Registrar usuario");
        System.out.println("2. Registrar factura");
        System.out.println("3. Consultar usuarios en base de datos");
        System.out.println("4. Consultar facturas en base de datos");
        System.out.println("5. Calcular impuesto");
        System.out.println("6. Calcular total");
        System.out.println("0. Salir");
    }

    
    private static void registrarUsuario(Scanner scanner) {
        System.out.println("--- Registrar usuario ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefono: ");
        String telefono = scanner.nextLine();

        Usuario usuario = new Usuario(nombre, email, telefono);

        if (!usuario.datosValidos()) {
            System.out.println("Error: todos los campos son obligatorios.");
            return;
        }

        try (Connection conn = BaseDatos.getConnection()) {
            if (Usuario.emailExiste(conn, email)) {
                System.out.println("Error: ya existe un usuario con ese email.");
                return;
            }
            usuario.registrar(conn);
            System.out.println("Usuario registrado con exito. Id asignado: " + usuario.getId());
        } catch (SQLException e) {
            System.out.println("Error al registrar el usuario: " + e.getMessage());
        }
    }

    
    private static void registrarFactura(Scanner scanner) {
        System.out.println("--- Registrar factura ---");
        System.out.print("Numero: ");
        String numero = scanner.nextLine();
        System.out.print("Concepto: ");
        String concepto = scanner.nextLine();
        double subtotal = leerDouble(scanner, "Subtotal: ");
        double porcentajeImpuesto = leerDouble(scanner, "Porcentaje de impuesto: ");
        System.out.print("Tipo de factura: ");
        String tipo = scanner.nextLine();
        int usuarioId = leerEntero(scanner, "Id del usuario propietario: ");

        Factura factura = new Factura(numero, concepto, subtotal, porcentajeImpuesto, tipo, usuarioId);

        if (!factura.datosValidos()) {
            System.out.println("Error: revise que ningun campo este vacio y que subtotal/impuesto no sean negativos.");
            return;
        }

        try (Connection conn = BaseDatos.getConnection()) {
            Usuario propietario = Usuario.buscarPorId(conn, usuarioId);
            if (propietario == null) {
                System.out.println("Error: no existe un usuario con ese id.");
                return;
            }
            factura.registrar(conn);
            System.out.println("Factura registrada con exito. Id asignado: " + factura.getId());
        } catch (SQLException e) {
            System.out.println("Error al registrar la factura: " + e.getMessage());
        }
    }

    
    private static void consultarUsuarios() {
        System.out.println("--- Usuarios registrados ---");
        try (Connection conn = BaseDatos.getConnection()) {
            List<Usuario> usuarios = Usuario.listarTodos(conn);
            if (usuarios.isEmpty()) {
                System.out.println("No hay usuarios registrados.");
            }
            for (Usuario u : usuarios) {
                System.out.println("Id: " + u.getId() + " | Nombre: " + u.getNombre()
                        + " | Email: " + u.getEmail() + " | Telefono: " + u.getTelefono());
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar usuarios: " + e.getMessage());
        }
    }

    
    private static void consultarFacturas() {
        System.out.println("--- Facturas registradas ---");
        try (Connection conn = BaseDatos.getConnection()) {
            List<Factura> facturas = Factura.listarTodas(conn);
            if (facturas.isEmpty()) {
                System.out.println("No hay facturas registradas.");
            }
            for (Factura f : facturas) {
                Usuario propietario = Usuario.buscarPorId(conn, f.getUsuarioId());
                String nombrePropietario = (propietario != null) ? propietario.getNombre() : "Desconocido";

                System.out.println("Id: " + f.getId()
                        + " | Numero: " + f.getNumero()
                        + " | Concepto: " + f.getConcepto()
                        + " | Subtotal: " + f.getSubtotal()
                        + " | Impuesto: " + f.calcularImpuesto()
                        + " | Tipo: " + f.getTipo()
                        + " | Propietario: " + nombrePropietario);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar facturas: " + e.getMessage());
        }
    }

   
    private static void calcularImpuesto(Scanner scanner) {
        int id = leerEntero(scanner, "Ingrese el id de la factura: ");
        try (Connection conn = BaseDatos.getConnection()) {
            Factura factura = Factura.buscarPorId(conn, id);
            if (factura == null) {
                System.out.println("No existe una factura con ese id.");
                return;
            }
            // Se usa una referencia de tipo FacturaOperacion para demostrar polimorfismo
            FacturaOperacion operacion = factura;
            System.out.println("Impuesto de la factura: " + operacion.calcularImpuesto());
        } catch (SQLException e) {
            System.out.println("Error al calcular el impuesto: " + e.getMessage());
        }
    }

    
    private static void calcularTotal(Scanner scanner) {
        int id = leerEntero(scanner, "Ingrese el id de la factura: ");
        try (Connection conn = BaseDatos.getConnection()) {
            Factura factura = Factura.buscarPorId(conn, id);
            if (factura == null) {
                System.out.println("No existe una factura con ese id.");
                return;
            }
            FacturaOperacion operacion = factura;
            System.out.println("Total de la factura: " + operacion.calcularTotal());
        } catch (SQLException e) {
            System.out.println("Error al calcular el total: " + e.getMessage());
        }
    }

    private static int leerEntero(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.println("Debe ingresar un numero entero.");
            System.out.print(mensaje);
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private static double leerDouble(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextDouble()) {
            System.out.println("Debe ingresar un numero valido.");
            System.out.print(mensaje);
            scanner.next();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }
}