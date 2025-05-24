package techlab.proyectoJava;

public class Product {
    private static int NEXT_ID = 1;
    private final int id;
    private String name;
    private int stock;
    private double price;

    public Product(String name, int stock, double price){
        this.name= name;
        this.stock = stock;
        this.price = price;
        this.id = NEXT_ID;
        NEXT_ID++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void info(){
        System.out.printf("""
                Id: %s | Nombre: %s
                --------------------------------
                Precio: $ %s | Stock: %s
                
                ================================
                """, this.id, this.name, this.price, this.stock);
    }
}
