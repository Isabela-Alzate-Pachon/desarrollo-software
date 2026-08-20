package encapsulamiento;

import java.math.BigDecimal;

public class Cuenta {
    private final String numero;
    private final String titular;
    private BigDecimal saldo;

    public Cuenta(String numero, String titular, BigDecimal saldoInicial) {
        if (saldoInicial.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        this.numero  = numero;
        this.titular = titular;
        this.saldo   = saldoInicial;
    }

    public void depositar(BigDecimal monto) {
        this.saldo = this.saldo.add(monto);
    }

    public void debitar(BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("El monto debe ser positivo");
        if (monto.compareTo(this.saldo) > 0)
            throw new SaldoInsuficienteException("Saldo: " + saldo + ", solicitado: " + monto);
        this.saldo = this.saldo.subtract(monto);
    }

    public BigDecimal getSaldo() { return this.saldo; }
    public String getNumero() { return numero; }
    public String getTitular() { return titular; }
}