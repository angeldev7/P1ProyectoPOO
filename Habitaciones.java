
package paqueteprincipal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Habitaciones {
    private ReservaHabitacion[] habitaciones = new ReservaHabitacion[15];
    private Scanner sc = new Scanner(System.in);
    private CSVManager csvManager = new CSVManager();
    private String rutaCSV = "reservas.csv";
    
    public Habitaciones() {
        // Cargar reservas desde el CSV y asignarlas a las habitaciones
        List<Reservas> reservasCargadas = csvManager.leerReservas(rutaCSV);
        for (int i = 0; i < habitaciones.length; i++) {
            habitaciones[i] = new ReservaHabitacion(i + 1);
        }
        int idx = 0;
        for (Reservas reserva : reservasCargadas) {
            if (idx < habitaciones.length) {
                habitaciones[idx].setReserva(reserva);
                idx++;
            }
        }
    }
    
    // Guarda todas las reservas actuales en el CSV (sobrescribe)
    public void guardarReservasCSV() {
        List<Reservas> reservasActuales = new ArrayList<>();
        for (ReservaHabitacion h : habitaciones) {
            if (h.isOcupada()) {
                reservasActuales.add(h.getReserva());
            }
        }
        csvManager.escribirReservas(rutaCSV, reservasActuales);
    }

    public void mostrarHabitaciones() {
        char opc;
        do {
            System.out.println("== Gestion de Hotel ==");
            System.out.println("1. Ver habitaciones disponibles");
            System.out.println("2. Ver habitaciones ocupadas");
            System.out.println("3. Check-in (nueva reserva)");
            System.out.println("4. Anular reserva");
            System.out.println("5. Salir");
            opc = sc.next().charAt(0);
            sc.nextLine();
            switch (opc) {
                case '1':
                    mostrarDisponibles();
                    break;
                case '2':
                    mostrarOcupadas();
                    break;
                case '3':
                    new Checkin(this).realizarCheckin(rutaCSV);
                    break;
                case '4':
                    anularReserva(rutaCSV);
                    break;
                case '5':
                    System.out.println("BYE BYE");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (opc != '5');
    }

    public void mostrarDisponibles() {
        System.out.println("Habitaciones disponibles:");
        for (ReservaHabitacion h : habitaciones) {
            if (!h.isOcupada()) {
                System.out.println("Habitacion " + h.getNumero());
            }
        }
    }

    public void mostrarOcupadas() {
        System.out.println("Habitaciones ocupadas:");
        for (ReservaHabitacion h : habitaciones) {
            if (h.isOcupada()) {
                String nombreCompleto = h.getReserva().getCliente().getNombre() + " " + h.getReserva().getCliente().getApellido();
                System.out.println("Habitacion " + h.getNumero() + " - Nombre: " + nombreCompleto);
            }
        }
    }

    public boolean realizarCheckin(String nombre, String apellido, long cedula, long telefono, int numDias, String rutaCSV) {
        for (ReservaHabitacion h : habitaciones) {
            if (!h.isOcupada()) {
                Clientes cliente = new Clientes(nombre, apellido, cedula, telefono);
                Reservas reserva = new Reservas(numDias, cliente);
                h.setReserva(reserva);
                // Guardar solo la nueva reserva (append)
                csvManager.crearReserva(reserva, rutaCSV, h.getNumero());
                System.out.println("Check-in realizado en la habitacion " + h.getNumero());
                return true;
            }
        }
        System.out.println("No hay habitaciones disponibles.");
        return false;
    }

    public void anularReserva(String rutaCSV) {
        System.out.print("Ingrese el nombre completo del cliente para anular la reserva: ");
        String nombreCompletoInput = sc.nextLine().trim().toLowerCase();
        for (ReservaHabitacion h : habitaciones) {
            if (h.isOcupada()) {
                String nombreCompletoReserva = (h.getReserva().getCliente().getNombre() + " " + h.getReserva().getCliente().getApellido()).trim().toLowerCase();
                if (nombreCompletoReserva.equals(nombreCompletoInput)) {
                    h.setReserva(null);
                    // Eliminar la reserva del CSV
                    csvManager.eliminarReserva(nombreCompletoReserva, rutaCSV);
                    System.out.println("Reserva anulada para: " + nombreCompletoReserva);
                    return;
                }
            }
        }
        System.out.println("No se encontro una reserva con ese nombre.");
    }

    // Clase interna para representar una habitación y su reserva
    private static class ReservaHabitacion {
        private int numero;
        private Reservas reserva;
        public ReservaHabitacion(int numero) {
            this.numero = numero;
        }

        public int getNumero() {
            return numero;
        }

        public Reservas getReserva() {
            return reserva;
        }

        public void setReserva(Reservas reserva) {
            this.reserva = reserva;
        }

        public boolean isOcupada() {
            return reserva != null;
        }
    }
}
