package com.lucasmoraist.payments_simplified.domain.enums;

public enum DocumentType {
    CPF,
    CNPJ,
    RG;

    public static DocumentType fromValue(String value) {
        if (value == null) return null;

        String cleaned = value.replaceAll("[^0-9Xx]", "");

        if (cleaned.matches("\\d{11}")) return CPF;
        if (cleaned.matches("\\d{14}")) return CNPJ;
        if (cleaned.matches("[0-9Xx]{5,9}")) return RG;

        return null;
    }

}
