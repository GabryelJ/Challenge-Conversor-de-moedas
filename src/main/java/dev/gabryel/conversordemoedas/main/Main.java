package dev.gabryel.conversordemoedas.main;

import com.google.gson.Gson;
import dev.gabryel.conversordemoedas.ui.UserInteractionHandler;
import dev.gabryel.conversordemoedas.currency.currencyrequest.CurrencyRequest;
import dev.gabryel.conversordemoedas.currency.model.Currency;
import dev.gabryel.conversordemoedas.propertyloader.PropertiesLoader;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Scanner;


public class Main {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        PropertiesLoader propertiesLoader = new PropertiesLoader();

        boolean exit = false;
        double currencyToExchangeAmount = 0f;
        String fromCurrency = "";
        String toCurrency = "";
        String controlAnotherConvertion = "";
        UserInteractionHandler currencyProcessing = new UserInteractionHandler(input);

        Gson gson = new Gson();

        String apiKey = propertiesLoader.getProperty("api.key");


        while(!exit){
            try{
                fromCurrency = currencyProcessing.pickCurrency();
                toCurrency = currencyProcessing.pickCurrency();
                currencyToExchangeAmount = currencyProcessing.pickCurrencyAmount();

                CurrencyRequest currencyRequest = new CurrencyRequest(apiKey);
                HttpResponse<String> response = currencyRequest.getCurrency(fromCurrency, toCurrency, currencyToExchangeAmount);
                Currency currency = gson.fromJson(response.body(), Currency.class);
                System.out.println(currency);
            }catch (IllegalArgumentException exception){
                System.err.println("Argumento invalido: " + exception.getMessage());
            }catch (IOException exception){
                System.err.println("Excecao de E/S: " + exception.getMessage());
            }catch (Exception exception){
                System.err.println("Excecao inesperada: " + exception.getMessage());
            }

            while(!controlAnotherConvertion.equals("sim") && !controlAnotherConvertion.equals("nao")) {
                System.out.println("Deseja continuar realizar uma nova conversao? ");
                System.out.println("Insira sim para realizar uma nova conversao. ");
                System.out.println("Insira nao (sem acento) para encerrar. ");
                controlAnotherConvertion = input.nextLine();
                if (controlAnotherConvertion.equals("nao")) {
                    exit = true;
                }
                if (!controlAnotherConvertion.equals("sim") && !controlAnotherConvertion.equals("nao")){
                    System.err.println("Insira sim para continuar ou nao para encerrar.");
                }
            }
            currencyToExchangeAmount = -99;
            fromCurrency = "";
            toCurrency = "";
            controlAnotherConvertion = "";
            currencyProcessing.reset();
        }
    }
}
