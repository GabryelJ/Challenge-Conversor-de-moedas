package dev.gabryel.conversordemoedas.currency.currencyrequest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyRequest {
    private final String apiKey;
    private String baseUrl;
    private final HttpClient client;

    public CurrencyRequest(String apiKey) {
        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new IllegalArgumentException("A chave de API nao pode ser nula ou vazia.");
        }
        this.apiKey = apiKey;
        this.baseUrl = "https://v6.exchangerate-api.com/v6/" + this.apiKey + "/pair/";
        this.client = HttpClient.newHttpClient();
    }

    public HttpResponse<String> getCurrency(String currencyToExchangeAbbreviation, String targetCurrencyAbbreviation, Double currencyToExchangeAmount) throws IOException, InterruptedException {
        if (currencyToExchangeAbbreviation == null ||
                currencyToExchangeAbbreviation.trim().isEmpty() ||
                targetCurrencyAbbreviation == null ||
                targetCurrencyAbbreviation.trim().isEmpty() ||
                currencyToExchangeAmount == null ||
                currencyToExchangeAmount < 0
        ) {
            throw new IllegalArgumentException("A abreviação da moeda nao pode ser nula ou vazia.");
        }

        URI uri = URI.create(baseUrl + currencyToExchangeAbbreviation + "/" + targetCurrencyAbbreviation + "/" + currencyToExchangeAmount);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
