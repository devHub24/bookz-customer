package com.sk.bookz_customer.validation.rules;

import com.sk.bookz_customer.constants.CharacterSet;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CharacterRule implements PasswordRule{
    private CharacterSet characterSet;
    private int minNumberOfCharacters;

    public CharacterRule(CharacterSet characterSet, int minNumberOfCharacters) {
        this.characterSet = characterSet;
        this.minNumberOfCharacters = minNumberOfCharacters>0?minNumberOfCharacters:1;
    }

    @Override
    public boolean validate(String password) {
        int i=0;
        int matches =0;
        while(matches<this.minNumberOfCharacters && i<password.length()){
            if(this.characterSet.getCharacters().contains(String.valueOf(password.charAt(i)))){
                matches++;
            }
            i++;
        }
        return matches==this.minNumberOfCharacters;
    }

    @Override
    public String getErrorMessage() {
        return this.characterSet.getErrorMessage();
    }
}
