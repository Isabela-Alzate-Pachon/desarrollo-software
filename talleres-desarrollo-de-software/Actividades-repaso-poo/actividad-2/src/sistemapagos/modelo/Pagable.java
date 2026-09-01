package sistemapagos.modelo;

import java.math.BigDecimal;
import sistemapagos.excepciones.SaldoInsuficienteException;

public interface Pagable {

    void procesarPago(BigDecimal monto) throws SaldoInsuficienteException;
}
