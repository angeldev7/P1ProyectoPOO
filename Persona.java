
package paqueteprincipal;

// HERENCIA: Esta clase es la superclase base para cualquier persona en el sistema (por ejemplo, Cliente, Empleado, etc.)
public abstract class Persona {
    // HERENCIA: Los atributos y metodos definidos aqui seran heredados por las subclases
    protected String nombre;
    protected String apellido;
    protected long cedulaIdentidad;
    protected long telefono;

    public Persona(String nombre, String apellido, long cedulaIdentidad, long telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedulaIdentidad = cedulaIdentidad;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public long getCedulaIdentidad() {
        return cedulaIdentidad;
    }

    public long getTelefono() {
        return telefono;
    }

    // POLIMORFISMO: Este metodo es abstracto y sera implementado de forma diferente en cada subclase
    public abstract String getTipoPersona();

    // POLIMORFISMO: Este metodo puede ser sobrescrito en las subclases para mostrar informacion especifica
    @Override
    public String toString() {
        return getTipoPersona() + ": " + nombre + " " + apellido + ", CI: " + cedulaIdentidad + ", Tel: " + telefono;
    }
}