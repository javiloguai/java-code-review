package schwarz.jobs.interview.coupon.core.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;
import schwarz.jobs.interview.coupon.core.domain.CouponEntity;
import schwarz.jobs.interview.coupon.core.repository.CouponRepository;
import schwarz.jobs.interview.coupon.core.services.model.Basket;
import schwarz.jobs.interview.coupon.web.dto.CouponDTO;
import schwarz.jobs.interview.coupon.web.dto.CouponRequestDTO;

@Service
@Validated
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    public Optional<CouponEntity> getCoupon(@NotBlank final String code) {
        return couponRepository.findByCode(code);
    }

    public Optional<Basket> apply(@Valid final Basket basket, @NotBlank final String code) {

        return getCoupon(code).map(couponEntity -> {

            final BigDecimal minBasketValue = couponEntity.getMinBasketValue();

            if (minBasketValue != null && basket.getValue().compareTo(minBasketValue) < 0) {
                throw new MinBasketValueNotMetException(code, basket.getValue(), minBasketValue);
            }

            basket.applyDiscount(couponEntity.getDiscount());

            return basket;
        });
    }

    public CouponEntity createCoupon(@Valid final CouponDTO couponDTO) {

        final CouponEntity couponEntity = CouponEntity.builder()
            .code(couponDTO.getCode().toLowerCase())
            .discount(couponDTO.getDiscount())
            .minBasketValue(couponDTO.getMinBasketValue())
            .build();

        return couponRepository.save(couponEntity);
    }

    public List<CouponEntity> getCoupons(@Valid final CouponRequestDTO couponRequestDTO) {

        final ArrayList<CouponEntity> foundCouponEntities = new ArrayList<>();

        couponRequestDTO.getCodes().forEach(code -> foundCouponEntities.add(couponRepository.findByCode(code).get()));

        return foundCouponEntities;
    }
}
