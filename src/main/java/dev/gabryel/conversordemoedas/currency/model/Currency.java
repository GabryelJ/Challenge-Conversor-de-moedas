package dev.gabryel.conversordemoedas.currency.model;

public record Currency(
        String base_code,
        String target_code,
        String conversion_result
) {}
