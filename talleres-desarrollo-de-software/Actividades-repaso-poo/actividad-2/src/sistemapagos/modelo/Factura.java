package sistemapagos.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Factura {

    private String numeroFactura;
    private Cliente cliente;
    private String servicio;
    private BigDecimal valor;
    private String estado;
    private LocalDate fechaVencimiento;

    public Factura(String numeroFactura, Cliente cliente, String servicio, BigDecimal valor, LocalDate fechaVencimiento) {
        this.numeroFactura = numeroFactura;
        this.cliente = cliente;
        this.servicio = servicio;
        this.valor = valor;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = "PENDIENTE";
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getServicio() {
        return servicio;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void marcarComoPagada() {
        this.estado = "PAGADA";
    }
}