package com.sk.bookz_customer.analyzer;

import com.sk.bookz_customer.exception.UrlNotActiveException;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

public class UrlNotActiveFailureAnalyzer extends AbstractFailureAnalyzer<UrlNotActiveException> {
    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, UrlNotActiveException cause) {
        return new FailureAnalysis("Url is not accessible: "+cause.getUrl(),"Validate the url",cause);
    }
}
