
package paqueteprincipal;
public class Reservas {
    private int numDias;
    private Clientes cliente;

    public Reservas(int numDias, Clientes cliente) {
        this.numDias = numDias;
        this.cliente = cliente;
    }

    public int getNumDias() {
        return numDias;
    }

    public void setNumDias(int numDias) {
        this.numDias = numDias;
    }

    // INTEGRACION: Aqui se integra la jerarquia de Persona/Clientes al proyecto practico.
    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    // POLIMORFISMO EN TIEMPO DE EJECUCION: Se usa el metodo toString() sobrescrito de Cliente/Persona.
    // VALIDACION DINAMICA: Esto demuestra el comportamiento dinamico del polimorfismo.
    public String getNombreCompletoCliente() {
        return cliente.toString();
    }
}