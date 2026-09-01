package sistemapagos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import sistemapagos.excepciones.SaldoInsuficienteException;
import sistemapagos.modelo.Cliente;
import sistemapagos.modelo.Cuenta;
import sistemapagos.modelo.CuentaAhorros;
import sistemapagos.modelo.CuentaCorriente;
import sistemapagos.modelo.Factura;
import sistemapagos.modelo.Pago;
import sistemapagos.servicio.SistemaPagosService;

public class main {

    private static Scanner scanner = new Scanner(System.in);
    private static SistemaPagosService servicio = new SistemaPagosService();

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Selecciona una opción: ");

            switch (opcion) {
                case 1 -> crearCliente();
                case 2 -> crearCuenta();
                case 3 -> crearFactura();
                case 4 -> procesarPago();
                case 5 -> consultarSaldoCuenta();
                case 6 -> consultarPagosPorCliente();
                case 7 -> consultarFacturasPorCliente();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida, intenta de nuevo.");
            }

        } while (opcion != 0);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n===== SISTEMA DE PAGOS DE SERVICIOS PÚBLICOS =====");
        System.out.println("1. Crear cliente");
        System.out.println("2. Crear cuenta (Ahorros o Corriente)");
        System.out.println("3. Crear factura");
        System.out.println("4. Procesar pago");
        System.out.println("5. Consultar saldo de cuenta");
        System.out.println("6. Consultar pagos por cliente");
        System.out.println("7. Consultar facturas por cliente");
        System.out.println("0. Salir");
    }

    private static void crearCliente() {
        System.out.println("\n-- Crear cliente --");
        String id = leerTexto("ID del cliente: ");
        String nombre = leerTexto("Nombre: ");
        String email = leerEmail("Email: ");

        Cliente cliente = new Cliente(id, nombre, email);
        servicio.crearCliente(cliente);
        System.out.println("Cliente creado correctamente.");
    }

    private static void crearCuenta() {
        System.out.println("\n-- Crear cuenta --");
        String idCliente = leerTexto("ID del cliente dueño de la cuenta: ");
        Cliente cliente = servicio.buscarCliente(idCliente);

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        String numeroCuenta = leerTexto("Número de cuenta: ");
        BigDecimal saldoInicial = leerMontoPositivo("Saldo inicial (debe ser mayor a 0): ");

        int tipo = leerOpcionEntre("Tipo de cuenta: 1) Ahorros  2) Corriente\nSelecciona: ", 1, 2);

        Cuenta cuenta;
        if (tipo == 1) {
            double tasaInteres = leerDecimal("Tasa de interés (ej. 0.02): ");
            cuenta = new CuentaAhorros(numeroCuenta, cliente, saldoInicial, tasaInteres);
        } else {
            BigDecimal cupoSobregiro = leerMonto("Cupo de sobregiro: ");
            cuenta = new CuentaCorriente(numeroCuenta, cliente, saldoInicial, cupoSobregiro);
        }

        servicio.crearCuenta(cuenta);
        System.out.println("Cuenta creada correctamente.");
    }

    private static void crearFactura() {
        System.out.println("\n-- Crear factura --");
        String idCliente = leerTexto("ID del cliente: ");
        Cliente cliente = servicio.buscarCliente(idCliente);

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        String numeroFactura = leerTexto("Número de factura: ");
        String servicioFactura = leerTexto("Servicio (Agua, Luz, Gas...): ");
        BigDecimal valor = leerMontoPositivo("Valor de la factura: ");

        Factura factura = new Factura(numeroFactura, cliente, servicioFactura, valor, LocalDate.now().plusDays(30));
        servicio.crearFactura(factura);
        System.out.println("Factura creada correctamente, estado: " + factura.getEstado());
    }

    private static void procesarPago() {
        System.out.println("\n-- Procesar pago --");
        String idCliente = leerTexto("ID del cliente: ");
        String numeroCuenta = leerTexto("Número de cuenta desde la cual se paga: ");
        String numeroFactura = leerTexto("Número de factura a pagar: ");
        BigDecimal monto = leerMontoPositivo("Monto a pagar: ");

        try {
            Pago pago = servicio.procesarPago(idCliente, numeroCuenta, numeroFactura, monto);
            System.out.println("Pago procesado con éxito. ID de pago: " + pago.getIdPago());
        } catch (SaldoInsuficienteException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void consultarSaldoCuenta() {
        System.out.println("\n-- Consultar saldo --");
        String numeroCuenta = leerTexto("Número de cuenta: ");

        try {
            BigDecimal saldo = servicio.obtenerSaldoCuenta(numeroCuenta);
            System.out.println("Saldo disponible: " + saldo);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void consultarPagosPorCliente() {
        System.out.println("\n-- Pagos por cliente --");
        String idCliente = leerTexto("ID del cliente: ");
        List<Pago> pagos = servicio.obtenerPagosPorCliente(idCliente);

        if (pagos.isEmpty()) {
            System.out.println("Este cliente no tiene pagos registrados.");
            return;
        }

        for (Pago p : pagos) {
            System.out.println(p.getIdPago() + " | " + p.getFecha() + " | Monto: " + p.getMonto()
                    + " | Factura: " + p.getFactura().getNumeroFactura());
        }
    }

    private static void consultarFacturasPorCliente() {
        System.out.println("\n-- Facturas por cliente --");
        String idCliente = leerTexto("ID del cliente: ");
        List<Factura> facturas = servicio.obtenerFacturasPorCliente(idCliente);

        if (facturas.isEmpty()) {
            System.out.println("Este cliente no tiene facturas registradas.");
            return;
        }

        for (Factura f : facturas) {
            System.out.println(f.getNumeroFactura() + " | " + f.getServicio()
                    + " | Valor: " + f.getValor() + " | Estado: " + f.getEstado());
        }
    }

    // ===================== Utilidades de lectura =====================

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor ingresa un número válido.");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private static double leerDecimal(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextDouble()) {
            System.out.println("Por favor ingresa un número válido.");
            scanner.next();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }

    private static BigDecimal leerMonto(String mensaje) {
        return BigDecimal.valueOf(leerDecimal(mensaje));
    }

    private static String leerEmail(String mensaje) {
        String email;
        do {
            email = leerTexto(mensaje);
            if (!email.contains("@")) {
                System.out.println("Email inválido, debe contener '@'. Intenta de nuevo.");
            }
        } while (!email.contains("@"));
        return email;
    }

    private static BigDecimal leerMontoPositivo(String mensaje) {
        BigDecimal monto;
        do {
            monto = leerMonto(mensaje);
            if (monto.compareTo(BigDecimal.ZERO) <= 0) {
                System.out.println("El monto debe ser mayor a 0. Intenta de nuevo.");
            }
        } while (monto.compareTo(BigDecimal.ZERO) <= 0);
        return monto;
    }

    private static int leerOpcionEntre(String mensaje, int min, int max) {
        int opcion;
        do {
            opcion = leerEntero(mensaje);
            if (opcion < min || opcion > max) {
                System.out.println("Opción inválida. Debe ser " + min + " o " + max + ".");
            }
        } while (opcion < min || opcion > max);
        return opcion;
    }
}