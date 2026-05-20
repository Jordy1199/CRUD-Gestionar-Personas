public class Persona {

    protected String cedula;
    protected String nombreCompleto;
    protected int edad;

    public Persona(String cedula, String nombreCompleto, int edad) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public int getEdad() {
        return edad;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void mostrarDatos() {
        System.out.println("Cedula: " + cedula);
        System.out.println("Nombre: " + nombreCompleto);
        System.out.println("Edad: " + edad);
    }
}
