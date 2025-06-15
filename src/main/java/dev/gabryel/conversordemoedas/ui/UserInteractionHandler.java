package dev.gabryel.conversordemoedas.ui;

import dev.gabryel.conversordemoedas.currency.model.CurrencyAbbreviation;

import java.util.*;
import java.util.regex.Pattern;

public class UserInteractionHandler {
    private Set<CurrencyAbbreviation> choosenCurrencies = new HashSet<>();
    private Scanner input;

    public UserInteractionHandler(Scanner input) {
        this.input = input;
    }

    private void showAvailableCurrencies() {
        System.out.println("Moedas disponiveis: ");
        for (CurrencyAbbreviation currency : CurrencyAbbreviation.values()) {
            if (!choosenCurrencies.contains(currency)) {
                System.out.println(currency.name() + ":" + currency.getId());
            }
        }
    }

    public Double pickCurrencyAmount(){
        Pattern numberPattern = Pattern.compile("^\\d+(\\.\\d+)?$");
        boolean validAmount = false;
        String amount = "";

        while(!validAmount){
            System.out.println("Insira a quantidade que sera convertida: ");
            amount = input.nextLine();
            if (amount.isEmpty()) {
                System.err.println("A entrada nao pode ser vazia.");
                continue;
            }
            if (numberPattern.matcher(amount).matches()){
                if ((Double.valueOf(amount) <= 0)){
                    System.err.println("A entrada nao pode ser negativa.");
                    continue;
                }
                validAmount = true;
            }
        }
        return Double.valueOf(amount);
    }

    public String pickCurrency(){
        boolean validPick = false;
        String id = "";
        Pattern numberPattern = Pattern.compile("\\d");

        while(!validPick) {
            System.out.println("Insira o numero associado as abreviacoes das moedas a seguir para escolher. ");
            this.showAvailableCurrencies();
            System.out.print("Escolha uma moeda: ");
            id = input.nextLine();
            if (numberPattern.matcher(id).matches()) {
                if (Integer.valueOf(id) >= CurrencyAbbreviation.LOWEST_ID && Integer.valueOf(id) <= CurrencyAbbreviation.HIGHEST_ID) {
                    if (!choosenCurrencies.contains(CurrencyAbbreviation.fromId(Integer.valueOf(id)))) {
                        validPick = true;
                        choosenCurrencies.add(CurrencyAbbreviation.fromId(Integer.valueOf(id)));
                    } else {
                        System.err.println("Moeda ja escolhida! Tente novamente.");
                    }
                } else {
                    System.err.println("Moeda nao existe! Tente novamente.");
                }
            }else{
                System.err.println("Entrada invalida! Tente novamente.");
            }
        }
        return CurrencyAbbreviation.fromId(Integer.valueOf(id)).name();
    }

    public void reset(){
        this.choosenCurrencies.clear();
    }
}
