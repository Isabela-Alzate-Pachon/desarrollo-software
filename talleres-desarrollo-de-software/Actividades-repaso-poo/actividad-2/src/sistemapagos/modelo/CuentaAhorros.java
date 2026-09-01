package sistemapagos.modelo;

import java.math.BigDecimal;
import sistemapagos.excepciones.SaldoInsuficienteException;

public class CuentaAhorros extends Cuenta implements Pagable {

    private double tasaInteres;

    public CuentaAhorros(String numeroCuenta, Cliente cliente, BigDecimal saldoInicial, double tasaInteres) {
        super(numeroCuenta, cliente, saldoInicial);
        this.tasaInteres = tasaInteres;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    @Override
    public void retirar(BigDecimal monto) throws SaldoInsuficienteException {
        if (monto.compareTo(getSaldo()) > 0) {
            throw new SaldoInsuficienteException(
                "Saldo insuficiente en la cuenta de ahorros " + getNumeroCuenta());
        }
        setSaldo(getSaldo().subtract(monto));
    }

    @Override
    public BigDecimal obtenerSaldoDisponible() {
        return getSaldo();
    }

    @Override
    public void procesarPago(BigDecimal monto) throws SaldoInsuficienteException {
        retirar(monto);
    }
}