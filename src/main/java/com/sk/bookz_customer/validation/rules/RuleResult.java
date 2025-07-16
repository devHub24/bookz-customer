package com.sk.bookz_customer.validation.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class RuleResult {

    private boolean valid;
    private String message;

    public RuleResult() {
        this.valid = true;
    }

}
