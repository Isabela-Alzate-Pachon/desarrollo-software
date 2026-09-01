package sistemapagos.servicio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import sistemapagos.excepciones.SaldoInsuficienteException;
import sistemapagos.modelo.Cliente;
import sistemapagos.modelo.Cuenta;
import sistemapagos.modelo.Factura;
import sistemapagos.modelo.Pago;

public class SistemaPagosService {

    private List<Cliente> clientes = new ArrayList<>();
    private List<Cuenta> cuentas = new ArrayList<>();
    private List<Factura> facturas = new ArrayList<>();
    private List<Pago> pagos = new ArrayList<>();

    // ===================== CRUD Cliente =====================

    public void crearCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente buscarCliente(String idCliente) {
        for (Cliente c : clientes) {
            if (c.getIdCliente().equals(idCliente)) {
                return c;
            }
        }
        return null;
    }

    public void actualizarCliente(String idCliente, String nuevoNombre, String nuevoEmail) {
        Cliente cliente = buscarCliente(idCliente);
        if (cliente != null) {
            cliente.setNombre(nuevoNombre);
            cliente.setEmail(nuevoEmail);
        }
    }

    public void eliminarCliente(String idCliente) {
        clientes.removeIf(c -> c.getIdCliente().equals(idCliente));
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }

    // ===================== CRUD Cuenta =====================

    public void crearCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public Cuenta buscarCuenta(String numeroCuenta) {
        for (Cuenta c : cuentas) {
            if (c.getNumeroCuenta().equals(numeroCuenta)) {
                return c;
            }
        }
        return null;
    }
    public void actualizarCuenta(String numeroCuenta, String nuevoEstado) {
    Cuenta cuenta = buscarCuenta(numeroCuenta);
    if (cuenta != null) {
        if (nuevoEstado.equalsIgnoreCase("ACTIVA")) {
            cuenta.activar();
        } else if (nuevoEstado.equalsIgnoreCase("INACTIVA")) {
            cuenta.inactivar();
        }
    }
}

    public void eliminarCuenta(String numeroCuenta) {
        cuentas.removeIf(c -> c.getNumeroCuenta().equals(numeroCuenta));
    }

    public List<Cuenta> listarCuentas() {
        return cuentas;
    }

    // ===================== CRUD Factura =====================

    public void crearFactura(Factura factura) {
        facturas.add(factura);
    }

    public Factura buscarFactura(String numeroFactura) {
        for (Factura f : facturas) {
            if (f.getNumeroFactura().equals(numeroFactura)) {
                return f;
            }
        }
        return null;
    }

    public void actualizarFactura(String numeroFactura, BigDecimal nuevoValor, LocalDate nuevaFechaVencimiento) {
        Factura factura = buscarFactura(numeroFactura);
        if (factura != null) {
            factura.setValor(nuevoValor);
            factura.setFechaVencimiento(nuevaFechaVencimiento);
        }
    }

    public void eliminarFactura(String numeroFactura) {
        facturas.removeIf(f -> f.getNumeroFactura().equals(numeroFactura));
    }

    public List<Factura> listarFacturas() {
        return facturas;
    }

    // ===================== CRUD Pago =====================

    public void crearPago(Pago pago) {
        pagos.add(pago);
    }

    public Pago buscarPago(String idPago) {
        for (Pago p : pagos) {
            if (p.getIdPago().equals(idPago)) {
                return p;
            }
        }
        return null;
    }

    public void eliminarPago(String idPago) {
        pagos.removeIf(p -> p.getIdPago().equals(idPago));
    }

    public List<Pago> listarPagos() {
        return pagos;
    }

    // ===================== Funcionalidades =====================

    public Pago procesarPago(String idCliente, String numeroCuenta, String numeroFactura, BigDecimal monto)
            throws SaldoInsuficienteException {

        Cliente cliente = buscarCliente(idCliente);
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        Factura factura = buscarFactura(numeroFactura);

        if (cliente == null || cuenta == null || factura == null) {
            throw new IllegalArgumentException("Cliente, cuenta o factura no encontrados");
        }

        cuenta.retirar(monto);
        factura.marcarComoPagada();

        String idPago = "P" + (pagos.size() + 1);
        Pago pago = new Pago(idPago, cliente, cuenta, factura, monto, LocalDate.now());
        pagos.add(pago);

        return pago;
    }

    public BigDecimal obtenerSaldoCuenta(String numeroCuenta) {
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta == null) {
            throw new IllegalArgumentException("Cuenta no encontrada");
        }
        return cuenta.obtenerSaldoDisponible();
    }

    public List<Pago> obtenerPagosPorCliente(String idCliente) {
        List<Pago> resultado = new ArrayList<>();
        for (Pago p : pagos) {
            if (p.getCliente().getIdCliente().equals(idCliente)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public List<Factura> obtenerFacturasPorCliente(String idCliente) {
        List<Factura> resultado = new ArrayList<>();
        for (Factura f : facturas) {
            if (f.getCliente().getIdCliente().equals(idCliente)) {
                resultado.add(f);
            }
        }
        return resultado;
    }
}