package dev.gabryel.conversordemoedas.model;

public record Currency(
        String base_code,
        String target_code,
        String conversion_result
) {}
