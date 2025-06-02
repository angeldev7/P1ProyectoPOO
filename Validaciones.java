
package paqueteprincipal;
import java.util.Scanner;
public class Validaciones { 
    Scanner sc = new Scanner(System.in); 
    public int ValdarInt(String mensaje){
        while(true){
            try{
                System.out.print(mensaje);
                int input = sc.nextInt();
                sc.nextLine(); // Limpiar el buffer
                if(input <= 0){
                    throw new Exception("El numero debe ser mayor que cero");
                }
                return input;
            }catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
 public String ValdarString(String mensaje){
    while(true){
        try{
            System.out.print(mensaje);
            String input= sc.nextLine().trim();
            if(input.isEmpty()){
                throw new Exception("El campo no puede estar vacio");
            }
            return input;
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
 }

 public long ValdarLong(String mensaje){
    while(true){
        try{
            System.out.print(mensaje);
            String input = sc.nextLine().trim();
            if(input.isEmpty()){
                throw new Exception("El campo no puede estar vacio");
            }
            return Long.parseLong(input);
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
 }
 
    
}
