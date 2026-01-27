package testAPIMeteorologia;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class main {

	
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Elige una opción:");
            System.out.println("1. Buscar clima");
            System.out.println("2. Salir");
            opcion = teclado.nextInt();
            teclado.nextLine();

            if (opcion == 1) {
                System.out.println("¿Qué lugar quieres buscar?");
                String lugar = teclado.nextLine();

                System.out.println("Buscando clima para: " + lugar);

                try {
                	
                    HttpClient cliente = HttpClient.newHttpClient();
                    
                    String lugarCodificado = URLEncoder.encode(lugar, StandardCharsets.UTF_8);
                    
                    HttpRequest requestLugar = HttpRequest.newBuilder()
                            .uri(new URI(
                                "https://nominatim.openstreetmap.org/search?q=" 
                                + lugarCodificado 
                                + "&format=json&limit=1"
                            ))
                            .header("User-Agent", "AppClimaJava/1.0")
                            .GET()
                            .build();
                    
                    HttpResponse<String> responseLugar = cliente.send(
                    		requestLugar,
                            HttpResponse.BodyHandlers.ofString()
                        );
                    
                    String cuerpoLugar = responseLugar.body();
                                        
                    String lat = cuerpoLugar.split("\"lat\":\"")[1].split("\"")[0];
                    String lon = cuerpoLugar.split("\"lon\":\"")[1].split("\"")[0];
                    
                    HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI(
                            "https://api.open-meteo.com/v1/forecast?latitude="+lat+"&longitude="+lon+"&current_weather=true"
                        ))
                        .GET()
                        .build();

                    HttpResponse<String> response = cliente.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                    );
                    
                    String cuerpo = response.body();
                    
                    String temp = cuerpo
                    	    .split("\"current_weather\":")[1]
                    	    .split("\"temperature\":")[1]
                    	    .split(",")[0];                    
                    
                    System.out.println("temperatura: " + temp);
                    
                    System.out.println("Código de respuesta: " + response.statusCode());
                    System.out.println("Cuerpo de la respuesta: ");
                    System.out.println(response.body());
                   

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (opcion == 2) {
                System.out.println("Saliendo del programa...");
            } else {
                System.out.println("Opción no válida");
            }

        } while (opcion != 2);

        teclado.close();
	}

}
