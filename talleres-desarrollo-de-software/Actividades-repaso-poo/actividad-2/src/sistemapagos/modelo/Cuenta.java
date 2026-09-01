package sistemapagos.modelo;

import java.math.BigDecimal;
import sistemapagos.excepciones.SaldoInsuficienteException;

public abstract class Cuenta {

    private String numeroCuenta;
    private Cliente cliente;
    private BigDecimal saldo;
    private String estado;

    public Cuenta(String numeroCuenta, Cliente cliente, BigDecimal saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.cliente = cliente;
        this.saldo = saldoInicial;
        this.estado = "ACTIVA";
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public String getEstado() {
        return estado;
    }

    public void activar() {
        this.estado = "ACTIVA";
    }

    public void inactivar() {
        this.estado = "INACTIVA";
    }

    protected void setSaldo(BigDecimal nuevoSaldo) {
        this.saldo = nuevoSaldo;
    }

    public void depositar(BigDecimal monto) {
        this.saldo = this.saldo.add(monto);
    }

    public abstract void retirar(BigDecimal monto) throws SaldoInsuficienteException;

    public abstract BigDecimal obtenerSaldoDisponible();
}