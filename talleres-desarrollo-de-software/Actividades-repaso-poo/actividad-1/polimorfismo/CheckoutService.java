package polimorfismo;

public class CheckoutService {
    public void finalizarCompra(Pedido pedido, Pago metodoDePago) {
        metodoDePago.procesar(pedido.getTotal());
        System.out.println("Compra finalizada. Método: " + metodoDePago.getDescripcion());
    }
}