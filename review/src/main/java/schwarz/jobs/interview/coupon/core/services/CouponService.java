package schwarz.jobs.interview.coupon.core.services;

import schwarz.jobs.interview.coupon.core.domain.CouponEntity;
import schwarz.jobs.interview.coupon.core.services.model.Basket;
import schwarz.jobs.interview.coupon.web.dto.CreateCouponRequestDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Optional;

public interface CouponService {
    Optional<Basket> apply(@Valid Basket basket, @NotBlank String code);

    CouponEntity createCoupon(@Valid CreateCouponRequestDTO couponDTO);

    List<CouponEntity> getCoupons(@NotEmpty List<String> codes);
}
