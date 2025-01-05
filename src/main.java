import com.pafradev.conversorMonedas.modelos.ConsultaConversion;

import java.util.Locale;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        String opcion = "0";
        float cantidadAConvertir = 0;
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US);

        while (!opcion.equals("7")){
            System.out.print("******CONVERSOR DE MONEDAS******\n\n" +
                    "Seleccione una opción de conversión: \n" +
                    "1. Dolar           ==> Peso Mexicano\n" +
                    "2. Peso Mexicano   ==> Dolar\n" +
                    "3. Dolar           ==> Peso Colombiano\n" +
                    "4. Peso Colombiano ==> Dolar\n" +
                    "5. Dolar           ==> Euro\n" +
                    "6. Euro            ==> Dolar\n" +
                    "7. Salir\n" +
                    "Selección: ");
            opcion = sc.nextLine();

            if(opcion.matches("^[1-6]$")){
                System.out.print("Ingrese la cantidad que desea convertir:");
                try {
                    cantidadAConvertir = sc.nextFloat();
                } catch (RuntimeException e) {
                    throw new RuntimeException("Cantidad incorrecta, introduzca una cantidad numérica");
                }
                sc.nextLine();
                ConsultaConversion conversion = new ConsultaConversion();
                conversion.convertirMoneda(opcion,cantidadAConvertir);
                conversion.imprimirConversion();
            } else if (opcion.equals("7")) {
                System.out.println("Hasta luego!");
            } else {
                System.out.println("Opción Incorrecta.");
            }
        }
        sc.close();
    }
}
