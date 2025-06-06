package dev.gabryel.conversordemoedas.main;

import dev.gabryel.conversordemoedas.main.util.propertyloader.PropertyLoader;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        PropertyLoader propertyLoader = new PropertyLoader();
        Scanner input = new Scanner(System.in);
        String apiKey = propertyLoader.getProperty("api.key");
        System.out.println("Abreviação da moeda: ");
        String currencyAbbreviation = input.nextLine().toUpperCase();
        String address = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + currencyAbbreviation;
        CurrencyRequest currencyRequest = new CurrencyRequest(address);

        System.out.println(request);
    }
}
