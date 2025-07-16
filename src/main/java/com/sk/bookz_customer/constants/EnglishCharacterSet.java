package com.sk.bookz_customer.constants;

public enum EnglishCharacterSet implements CharacterSet {
    UPPERCASE("ABCDEFGHIJKLMNOPQRSTUVWXYZ","INSUFFICIENT_UPPERCASE_CHARACTERS"),
    LOWERCASE("abcdefghijklmnopqrstuvwxyz","INSUFFICIENT_LOWERCASE_CHARACTERS"),
    SPECIALCASE("!\\\"#$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~","INSUFFICIENT_SPECIAL_CHARACTERS"),
    DIGITS("1023456789","INSUFFICIENT_DIGITS");

    String characters;
    String errorMessage;
    EnglishCharacterSet(String characters, String errorMessage) {
        this.characters = characters;
        this.errorMessage = errorMessage;
    }
    @Override
    public String getCharacters() {
        return this.characters;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
