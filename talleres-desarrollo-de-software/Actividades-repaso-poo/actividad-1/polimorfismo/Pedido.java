package polimorfismo;

import java.math.BigDecimal;

public class Pedido {
    private final String descripcion;
    private final BigDecimal total;

    public Pedido(String descripcion, BigDecimal total) {
        this.descripcion = descripcion;
        this.total = total;
    }

    public BigDecimal getTotal() { return total; }
    public String getDescripcion() { return descripcion; }
}