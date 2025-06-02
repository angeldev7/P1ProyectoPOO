
package paqueteprincipal;


// HERENCIA: Esta clase hereda de Persona y representa a un cliente del hotel.
// INTEGRACION: Esta clase se usa en el proyecto para crear reservas y gestionar clientes.
public class Clientes extends Persona {
    public Clientes(String nombre, String apellido, long cedulaIdentidad, long telefono) {
        super(nombre, apellido, cedulaIdentidad, telefono);
    }

    // POLIMORFISMO: Sobrescribe el metodo abstracto de Persona para indicar el tipo de persona.
    @Override
    public String getTipoPersona() {
        return "Cliente";
    }

    // POLIMORFISMO: Sobrescribe el metodo toString para mostrar informacion especifica del cliente.
    @Override
    public String toString() {
        return super.toString();
    }  
    // Metodos setters si se requieren
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setCedulaIdentidad(long cedulaIdentidad) {
        this.cedulaIdentidad = cedulaIdentidad;
    }
    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }
}