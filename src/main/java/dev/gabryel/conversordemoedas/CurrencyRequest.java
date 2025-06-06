package dev.gabryel.conversordemoedas.main;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyRequest {
    private String address;

    public CurrencyRequest(String address){
        this.address = address;
    }

    public HttpResponse<String> getCurrency(){
        URI uri = URI.create(this.address);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response;
        }catch (InterruptedException exception){
            System.err.println(exception.getMessage());
        }catch (Exception exception){
            System.err.println("Exceção inesperada: " + exception.getMessage());
        }
        return null;
    }

}



public HttpResponse<String> getCurrency(){
    URI uri = URI.create(address);
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .GET()
            .build();
    try {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }catch (InterruptedException exception){
        System.err.println(exception.getMessage());
    }catch (Exception exception){
        System.err.println("Exceção inesperada: " + exception.getMessage());
    }
    return null;
}
