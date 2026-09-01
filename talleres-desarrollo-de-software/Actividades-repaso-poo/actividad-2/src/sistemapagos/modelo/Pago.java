package sistemapagos.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pago {

    private String idPago;
    private Cliente cliente;
    private Cuenta cuenta;
    private Factura factura;
    private BigDecimal monto;
    private LocalDate fecha;

    public Pago(String idPago, Cliente cliente, Cuenta cuenta, Factura factura, BigDecimal monto, LocalDate fecha) {
        this.idPago = idPago;
        this.cliente = cliente;
        this.cuenta = cuenta;
        this.factura = factura;
        this.monto = monto;
        this.fecha = fecha;
    }

    public String getIdPago() {
        return idPago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public Factura getFactura() {
        return factura;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
