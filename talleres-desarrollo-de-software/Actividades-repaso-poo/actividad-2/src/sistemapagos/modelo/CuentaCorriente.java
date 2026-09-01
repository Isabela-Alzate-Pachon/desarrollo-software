package sistemapagos.modelo;

import java.math.BigDecimal;
import sistemapagos.excepciones.SaldoInsuficienteException;

public class CuentaCorriente extends Cuenta implements Pagable {

    private BigDecimal cupoSobregiro;

    public CuentaCorriente(String numeroCuenta, Cliente cliente, BigDecimal saldoInicial, BigDecimal cupoSobregiro) {
        super(numeroCuenta, cliente, saldoInicial);
        this.cupoSobregiro = cupoSobregiro;
    }

    public BigDecimal getCupoSobregiro() {
        return cupoSobregiro;
    }

    @Override
    public void retirar(BigDecimal monto) throws SaldoInsuficienteException {
        BigDecimal disponible = obtenerSaldoDisponible();
        if (monto.compareTo(disponible) > 0) {
            throw new SaldoInsuficienteException(
                "Saldo y cupo de sobregiro insuficientes en la cuenta corriente " + getNumeroCuenta());
        }
        setSaldo(getSaldo().subtract(monto));
    }

    @Override
    public BigDecimal obtenerSaldoDisponible() {
        return getSaldo().add(cupoSobregiro);
    }

    @Override
    public void procesarPago(BigDecimal monto) throws SaldoInsuficienteException {
        retirar(monto);
    }
}
