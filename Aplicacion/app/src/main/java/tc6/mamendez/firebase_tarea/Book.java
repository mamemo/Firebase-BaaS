package tc6.mamendez.firebase_tarea;

public class Book {
    private String id;
    private String name;
    private String price;
    private String description;

    public Book(String id, String name, String price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Book(String name, String price, String descripcion) {
        this.name = name;
        this.price = price;
        this.description = descripcion;
    }

    public String getNombre() {
        return name;
    }

    public void setNombre(String nombre) {
        this.name = nombre;
    }

    public String getPrecio() {
        return price;
    }

    public void setPrecio(String price){
        this.price = price;
    }

    public String getDescripcion() {
        return description;
    }

    public void setDescripcion(String description) {
        this.description = description;
    }


}
