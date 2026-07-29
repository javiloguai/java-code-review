package schwarz.jobs.interview.coupon.core.services;

import schwarz.jobs.interview.coupon.core.services.model.command.CreateCouponCommand;
import schwarz.jobs.interview.coupon.core.services.model.domain.BasketDomain;
import schwarz.jobs.interview.coupon.core.services.model.domain.CouponDomain;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Business operations around coupons.
 */
public interface CouponService {

    /**
     * Applies a coupon to a basket, if the coupon exists and the basket qualifies for it.
     */
    BasketDomain apply(@Valid BasketDomain basket, @NotBlank String code);

    /**
     * Creates a new coupon.
     */
    CouponDomain createCoupon(@Valid CreateCouponCommand couponDTO);

    /**
     * Looks up coupons by code, skipping any code that doesn't exist.
     */
    List<CouponDomain> getCoupons(@NotEmpty List<String> codes);
}
