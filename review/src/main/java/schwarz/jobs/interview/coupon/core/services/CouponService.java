package schwarz.jobs.interview.coupon.core.services;

import schwarz.jobs.interview.coupon.core.persistence.entity.CouponEntity;
import schwarz.jobs.interview.coupon.core.services.model.BasketDomain;
import schwarz.jobs.interview.coupon.web.dto.request.CreateCouponRequestDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Optional;

public interface CouponService {
    Optional<BasketDomain> apply(@Valid BasketDomain basket, @NotBlank String code);

    CouponEntity createCoupon(@Valid CreateCouponRequestDTO couponDTO);

    List<CouponEntity> getCoupons(@NotEmpty List<String> codes);
}
