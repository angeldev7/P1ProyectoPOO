
package paqueteprincipal;

public class Checkin {

    private Habitaciones habitaciones;

    public Checkin(Habitaciones habitaciones) {
        this.habitaciones = habitaciones;
    }

    public void realizarCheckin(String rutaCSV) {
        Validaciones val = new Validaciones();
        System.out.println("--- CHECK-IN ---");
        String nombre = val.ValdarString("Nombre: ");
        String apellido = val.ValdarString("Apellido: ");
        long cedula = val.ValdarLong("Cedula de Identidad: ");
        long telefono = val.ValdarLong("Telefono: ");
        int numDias = val.ValdarInt("Numero de dias de la reserva: ");
        habitaciones.realizarCheckin(nombre, apellido, cedula, telefono, numDias, rutaCSV);
    }
}