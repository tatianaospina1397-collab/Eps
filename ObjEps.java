public class ObjEps{
    private int Id; 
    private String Nombre; 
    private int Edad; 
    private String Servicio; 
    private String Estado;
    
    public ObjEps() {
    }

    public ObjEps(int id, String nombre, int edad, String servicio, String estado) {
        Id = id;
        Nombre = nombre;
        Edad = edad;
        Servicio = servicio;
        Estado = estado;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }

    public String getServicio() {
        return Servicio;
    }

    public void setServicio(String servicio) {
        Servicio = servicio;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    
}