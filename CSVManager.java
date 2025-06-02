
package paqueteprincipal;
import java.io.*;
import java.util.*;

public class CSVManager {
    private static final String ENCABEZADO = "habitacion,numDias,nombreCompleto,cedula,telefono";

    // Crear (agregar) una reserva al archivo CSV
    public void crearReserva(Reservas reserva, String rutaArchivo, int habitacionNum) {
        boolean archivoExiste = new File(rutaArchivo).exists();
        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo, true))) {
            if (!archivoExiste) {
                writer.println(ENCABEZADO);
            }
            String[] datos = {
                String.valueOf(habitacionNum),
                String.valueOf(reserva.getNumDias()),
                reserva.getCliente().getNombre() + " " + reserva.getCliente().getApellido(),
                String.valueOf(reserva.getCliente().getCedulaIdentidad()),
                String.valueOf(reserva.getCliente().getTelefono())
            };
            writer.println(String.join(",", datos));
        } catch (IOException e) {
            System.out.println("Error al crear reserva en el archivo CSV: " + e.getMessage());
        }
    }

    // Leer todas las reservas del archivo CSV
    public List<Reservas> leerReservas(String rutaArchivo) {
        List<Reservas> reservas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean esPrimera = true;
            while ((linea = br.readLine()) != null) {
                if (esPrimera) { // Saltar encabezado
                    esPrimera = false;
                    continue;
                }
                String[] datos = linea.split(",");
                if (datos.length >= 5) {
                    int numDias = Integer.parseInt(datos[1]);
                    String[] nombreApellido = datos[2].split(" ", 2);
                    String nombre = nombreApellido.length > 0 ? nombreApellido[0] : "";
                    String apellido = nombreApellido.length > 1 ? nombreApellido[1] : "";
                    long cedula = Long.parseLong(datos[3]);
                    long telefono = Long.parseLong(datos[4]);
                    Clientes cliente = new Clientes(nombre, apellido, cedula, telefono);
                    Reservas reserva = new Reservas(numDias, cliente);
                    reservas.add(reserva);
                }
            }
        } catch (FileNotFoundException e) {
            // No mostrar error si el archivo no existe
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al reconstruir reservas: " + e.getMessage());
        }
        return reservas;
    }

    // Actualizar una reserva existente (por nombre completo)
    public void actualizarReserva(Reservas reservaActualizada, String rutaArchivo) {
        List<Reservas> reservas = leerReservas(rutaArchivo);
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).getNombreCompletoCliente().equals(reservaActualizada.getNombreCompletoCliente())) {
                reservas.set(i, reservaActualizada);
                break;
            }
        }
        escribirReservas(rutaArchivo, reservas);
    }

    // Eliminar una reserva por nombre completo
    public void eliminarReserva(String nombreCompleto, String rutaArchivo) {
        String nombreNormalizado = nombreCompleto.trim().toLowerCase();
        List<Reservas> reservas = leerReservas(rutaArchivo);
        reservas.removeIf(r -> (r.getCliente().getNombre() + " " + r.getCliente().getApellido()).trim().toLowerCase().equals(nombreNormalizado));
        escribirReservas(rutaArchivo, reservas);
    }

    // Escribir toda la lista de reservas (sobrescribe el archivo)
    public void escribirReservas(String rutaArchivo, List<Reservas> reservas) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo))) {
            writer.println(ENCABEZADO);
            int habitacionNum = 1;
            for (Reservas reserva : reservas) {
                String[] datos = {
                    String.valueOf(habitacionNum),
                    String.valueOf(reserva.getNumDias()),
                    reserva.getCliente().getNombre() + " " + reserva.getCliente().getApellido(),
                    String.valueOf(reserva.getCliente().getCedulaIdentidad()),
                    String.valueOf(reserva.getCliente().getTelefono())
                };
                writer.println(String.join(",", datos));
                habitacionNum++;
            }
        } catch (IOException e) {
            System.out.println("Error al escribir reservas en el archivo CSV: " + e.getMessage());
        }
    }
}