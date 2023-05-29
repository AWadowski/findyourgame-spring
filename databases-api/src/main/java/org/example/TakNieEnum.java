package org.example;

/**
 * @author adam.wadowski
 * @since 29.05.2023
 */
public enum TakNieEnum {
    TAK("T"),
    NIE("N");

    private String code;

    private TakNieEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

