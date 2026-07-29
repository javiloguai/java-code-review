package schwarz.jobs.interview.coupon.core.services;

import java.math.BigDecimal;

public class MinBasketValueNotMetException extends RuntimeException {

    public MinBasketValueNotMetException(final String code, final BigDecimal basketValue, final BigDecimal minBasketValue) {
        super(String.format("Coupon '%s' requires a minimum basket value of %s, got %s", code, minBasketValue, basketValue));
    }

}
