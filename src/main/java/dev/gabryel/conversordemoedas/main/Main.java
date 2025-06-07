package dev.gabryel.conversordemoedas.main;

import com.google.gson.Gson;
import dev.gabryel.conversordemoedas.currencyrequest.CurrencyRequest;
import dev.gabryel.conversordemoedas.model.Currency;
import dev.gabryel.conversordemoedas.propertyloader.PropertiesLoader;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        Scanner input = new Scanner(System.in);
        String apiKey = propertiesLoader.getProperty("api.key");
        System.out.println("Insira abreviação da moeda que deseja converter: ");
        String currencyToExchangeAbbreviation = input.nextLine().toUpperCase();
        System.out.println("Insira abreviação da moeda que deseja converter: ");
        String targetCurrencyAbbreviation = input.nextLine().toUpperCase();
        double currencyToExchangeAmount;
        currencyToExchangeAmount = input.nextFloat();
        input.nextLine();
        Gson gson = new Gson();
        try{
            CurrencyRequest currencyRequest = new CurrencyRequest(apiKey);
            HttpResponse<String> response = currencyRequest.getCurrency(currencyToExchangeAbbreviation, targetCurrencyAbbreviation, currencyToExchangeAmount);
            Currency currency = gson.fromJson(response.body(), Currency.class);
            System.out.println(currency);

        }catch (IllegalArgumentException exception){
            System.err.println("Argumento inválido: " + exception.getMessage());
        }catch (IOException exception){
            System.err.println("Exceção de E/S: " + exception.getMessage());
        }catch (Exception exception){
            System.err.println("Exceção inesperada: " + exception.getMessage());
        }

    }
}
