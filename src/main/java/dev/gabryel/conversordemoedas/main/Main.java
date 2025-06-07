package dev.gabryel.conversordemoedas.main;

import dev.gabryel.conversordemoedas.currencyrequest.CurrencyRequest;
import dev.gabryel.conversordemoedas.propertyloader.PropertiesLoader;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        Scanner input = new Scanner(System.in);
        String apiKey = propertiesLoader.getProperty("api.key");
        System.out.println("Insira abreviação da moeda: ");
        String currencyAbbreviation = input.nextLine().toUpperCase();

        try{
            CurrencyRequest currencyRequest = new CurrencyRequest(apiKey);
            HttpResponse<String> response = currencyRequest.getCurrency(currencyAbbreviation);
            System.out.println(response.body());
        }catch (IllegalArgumentException exception){
            System.err.println("Argumento inválido: " + exception.getMessage());
        }catch (IOException exception){
            System.err.println("Exceção de E/S: " + exception.getMessage());
        }catch (Exception exception){
            System.err.println("Exceção inesperada: " + exception.getMessage());
        }

    }
}
