package techlab.proyectoJava;

public class Pedido {
    private final Product producto;
    private int cantidad;

    public Pedido(Product producto, int cantidad){
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Product getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getMontoTotal() {
        return this.cantidad * this.producto.getPrice();
    }

    public double getMontoUn() {
        return this.producto.getPrice();
    }

    public void info() {
        System.out.printf("""
            Producto: %s
            --------------------------------
            Cantidad: %d
            --------------------------------
            Precio Unitario: % .2f
            --------------------------------
            Total: $ %s
            ================================
            """, this.producto.getName(), this.cantidad, getMontoUn(), getMontoTotal());

    }
}
