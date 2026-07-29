package schwarz.jobs.interview.coupon.core.services;

import schwarz.jobs.interview.coupon.core.services.model.command.CreateCouponCommand;
import schwarz.jobs.interview.coupon.core.services.model.domain.BasketDomain;
import schwarz.jobs.interview.coupon.core.services.model.domain.CouponDomain;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public interface CouponService {
    BasketDomain apply(@Valid BasketDomain basket, @NotBlank String code);

    CouponDomain createCoupon(@Valid CreateCouponCommand couponDTO);

    List<CouponDomain> getCoupons(@NotEmpty List<String> codes);
}
