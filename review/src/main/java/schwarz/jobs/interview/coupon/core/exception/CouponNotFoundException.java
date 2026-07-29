package schwarz.jobs.interview.coupon.core.exception;

public class CouponNotFoundException extends RuntimeException {

    public CouponNotFoundException(final String code) {
        super(String.format("Coupon '%s' not found", code));
    }

}
