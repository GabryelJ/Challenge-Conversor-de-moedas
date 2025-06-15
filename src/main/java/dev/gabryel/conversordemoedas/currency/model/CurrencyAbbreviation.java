package dev.gabryel.conversordemoedas.currency.model;

public enum CurrencyAbbreviation {
    ARS(1, "Peso Argentino"),
    BRL(2, "Real Brasileiro"),
    USD(3, "Dólar Americano");

    private final int id;
    private final String fullName;
    public static final int LOWEST_ID = 1;
    public static final int HIGHEST_ID = values().length;

    CurrencyAbbreviation(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public int getId() {
        return this.id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public static CurrencyAbbreviation fromId(int id) {
        for (CurrencyAbbreviation currency : CurrencyAbbreviation.values()) {
            if (currency.getId() == id) {
                return currency;
            }
        }
        throw new IllegalArgumentException("Nenhuma moeda encontrada para o ID: " + id);
    }
}