package dev.gabryel.conversorDeMoedas.main;

import dev.gabryel.conversorDeMoedas.main.util.propertyLoader.PropertyLoader;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        PropertyLoader propertyLoader = new PropertyLoader();
        Scanner input = new Scanner(System.in);
        String apiKey = propertyLoader.getProperty("api.key");
        System.out.println("Abreviação da moeda: ");
        String currencyAbbreviation = input.nextLine().toUpperCase();
        String address = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + currencyAbbreviation;
        URI uri = URI.create(address);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                        .uri(uri)
                        .GET()
                        .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        }catch (InterruptedException exception){
            System.err.println(exception.getMessage());
        }catch (Exception exception){
            System.err.println("Exceção inesperada: " + exception.getMessage());
        }

        System.out.println(request);
    }
}
