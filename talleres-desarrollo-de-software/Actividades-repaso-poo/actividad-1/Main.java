import java.math.BigDecimal;
import java.util.Scanner;

import encapsulamiento.Cuenta;
import herencia.CuentaCorriente;
import polimorfismo.CheckoutService;
import polimorfismo.Pago;
import polimorfismo.PagoEfectivo;
import polimorfismo.PagoTarjeta;
import polimorfismo.PagoTransferencia;
import polimorfismo.Pedido;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elige una opcion: ");

            switch (opcion) {
                case 1:
                    gestionarCuentaAhorros(); // demuestra encapsulamiento
                    break;
                case 2:
                    gestionarCuentaCorriente(); // demuestra herencia
                    break;
                case 3:
                    realizarPago(); // demuestra polimorfismo
                    break;
                case 0:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opcion invalida. Elige un numero del menu.");
                    break;
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("=== Banco - Menu Principal ===");
        System.out.println("1. Abrir y gestionar cuenta de ahorros");
        System.out.println("2. Abrir y gestionar cuenta corriente (con cupo de sobregiro)");
        System.out.println("3. Realizar un pago");
        System.out.println("0. Salir");
    }

    private static void gestionarCuentaAhorros() {
        Cuenta cuenta = new Cuenta("001", "Isabela", new BigDecimal("100000"));
        System.out.println("Cuenta creada. Saldo inicial: " + cuenta.getSaldo());

        BigDecimal monto = leerMontoPositivo("Monto a depositar: ");
        cuenta.depositar(monto);
        System.out.println("Saldo tras deposito: " + cuenta.getSaldo());

        try {
            monto = leerMontoPositivo("Monto a debitar: ");
            cuenta.debitar(monto);
            System.out.println("Saldo tras debito: " + cuenta.getSaldo());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void gestionarCuentaCorriente() {
        CuentaCorriente cc = new CuentaCorriente("002", new BigDecimal("50000"), new BigDecimal("100000"));
        System.out.println("Cuenta corriente creada. Saldo inicial: " + cc.getSaldo());

        try {
            BigDecimal monto = leerMontoPositivo("Monto a debitar (puede usar el descubierto): ");
            cc.debitar(monto);
            System.out.println("Saldo tras debito: " + cc.getSaldo());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void realizarPago() {
        Pedido pedido = new Pedido("Compra de prueba", new BigDecimal("75000"));
        CheckoutService checkout = new CheckoutService();

        System.out.println("1. Tarjeta");
        System.out.println("2. Transferencia");
        System.out.println("3. Efectivo");

        int op = leerEnteroEnRango("Metodo de pago: ", 1, 3);

        Pago metodo;
        if (op == 1) {
            metodo = new PagoTarjeta("4111-XXXX-XXXX-1234");
        } else if (op == 2) {
            metodo = new PagoTransferencia("CBU-0000-1111");
        } else {
            metodo = new PagoEfectivo();
        }

        checkout.finalizarCompra(pedido, metodo);
    }

    // Pide un numero entero. Repite si no es un numero valido.
    private static int leerEntero(String mensaje) {
        int valor = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            String texto = sc.nextLine();
            try {
                valor = Integer.parseInt(texto.trim());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingresa un numero valido.");
            }
        }
        return valor;
    }

    // Pide un numero entero dentro de un rango especifico (ej. 1 a 3). Repite si no es valido o esta fuera de rango.
    private static int leerEnteroEnRango(String mensaje, int minimo, int maximo) {
        int valor;
        boolean valido = false;
        do {
            valor = leerEntero(mensaje);
            if (valor < minimo || valor > maximo) {
                System.out.println("Ingresa un numero entre " + minimo + " y " + maximo + ".");
            } else {
                valido = true;
            }
        } while (!valido);
        return valor;
    }

    // Pide un monto en dinero. Repite si no es un numero valido o si es cero o negativo.
    private static BigDecimal leerMontoPositivo(String mensaje) {
        BigDecimal valor = null;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            String texto = sc.nextLine();
            try {
                valor = new BigDecimal(texto.trim());
                if (valor.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("El monto debe ser mayor que cero.");
                } else {
                    valido = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingresa un monto valido (solo numeros).");
            }
        }
        return valor;
    }
}