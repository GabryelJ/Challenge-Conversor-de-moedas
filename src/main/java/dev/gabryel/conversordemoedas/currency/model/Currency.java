package dev.gabryel.conversordemoedas.currency.model;

public record Currency(
        String base_code,
        String target_code,
        String conversion_result

) {
    @Override
    public String toString() {
        return  "De: " + base_code + '\n' +
                "Para: " + target_code + '\n' +
                "Resultado obtido: " + Double.valueOf(conversion_result);
    }
}
