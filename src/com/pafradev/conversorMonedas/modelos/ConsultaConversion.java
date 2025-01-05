package com.pafradev.conversorMonedas.modelos;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaConversion {
    String monedaInicial = "";
    String monedaFinal = "";
    float resultadoConversion = 0;
    float cantidadAConvertir = 0;
    String json = "";

    public float convertirMoneda(String opcion, float cantidadAConvertir){
        this.cantidadAConvertir = cantidadAConvertir;
        switch (opcion){
            case "1":
                monedaInicial = "USD";
                monedaFinal = "MXN";
                break;
            case "2":
                monedaInicial = "MXN";
                monedaFinal = "USD";
                break;
            case "3":
                monedaInicial = "USD";
                monedaFinal = "COP";
                break;
            case "4":
                monedaInicial = "COP";
                monedaFinal = "USD";
                break;
            case "5":
                monedaInicial = "USD";
                monedaFinal = "EUR";
                break;
            case "6":
                monedaInicial = "EUR";
                monedaFinal = "USD";
            default:
                break;
        }

        String direccion = "https://v6.exchangerate-api.com/v6/f90db61f2b0398b6a7c29b8c/pair/"+monedaInicial+"/"+monedaFinal;

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            json = response.body();

            Moneda moneda = gson.fromJson(json, Moneda.class);
            resultadoConversion = moneda.conversion_rate()*cantidadAConvertir;

        }catch (Exception e){
            e.getMessage();
            resultadoConversion = 0;
        }
        return resultadoConversion;
    }

    public void imprimirConversion(){
        System.out.println("El valor " + cantidadAConvertir + " [" + monedaInicial + "] corresponde al valor final de ==>> " +
                resultadoConversion + " [" + monedaFinal + "]");
    }

}
