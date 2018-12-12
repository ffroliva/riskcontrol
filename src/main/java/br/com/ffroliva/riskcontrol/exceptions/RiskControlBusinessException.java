package br.com.ffroliva.riskcontrol.exceptions;

import lombok.Builder;

/**
 * Application's Business Exception.
 *
 * @author Flavio Oliva <a href="mailto:ffroliva@gmail.com">ffroliva@gmail.com</a>
 */
@Builder
public class RiskControlBusinessException extends Exception {

    private String message;

    @Builder
    public RiskControlBusinessException(String message) {
        super(message);
    }

    ;
}
