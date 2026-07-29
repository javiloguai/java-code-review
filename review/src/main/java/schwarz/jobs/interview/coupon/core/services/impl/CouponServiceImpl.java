package schwarz.jobs.interview.coupon.core.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import schwarz.jobs.interview.coupon.core.persistence.entity.CouponEntity;
import schwarz.jobs.interview.coupon.core.persistence.repository.CouponRepository;
import schwarz.jobs.interview.coupon.core.services.CouponService;
import schwarz.jobs.interview.coupon.core.exception.MinBasketValueNotMetException;
import schwarz.jobs.interview.coupon.core.services.model.BasketDomain;
import schwarz.jobs.interview.coupon.web.dto.request.CreateCouponRequestDTO;

@Service
@Validated
@RequiredArgsConstructor
@Slf4j
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    private Optional<CouponEntity> getCoupon(@NotBlank final String code) {
        return couponRepository.findByCodeIgnoreCase(code);
    }

    @Override
    public Optional<BasketDomain> apply(@Valid final BasketDomain basket, @NotBlank final String code) {

        return getCoupon(code).map(couponEntity -> {

            final BigDecimal minBasketValue = couponEntity.getMinBasketValue();

            if (minBasketValue != null && basket.getValue().compareTo(minBasketValue) < 0) {
                throw new MinBasketValueNotMetException(code, basket.getValue(), minBasketValue);
            }

            basket.applyDiscount(couponEntity.getDiscount());

            return basket;
        });
    }

    @Override
    public CouponEntity createCoupon(@Valid final CreateCouponRequestDTO couponDTO) {

        final CouponEntity couponEntity = CouponEntity.builder()
            .code(couponDTO.getCode().toUpperCase())
            .discount(couponDTO.getDiscount())
            .minBasketValue(couponDTO.getMinBasketValue())
            .build();

        return couponRepository.save(couponEntity);
    }

    @Override
    public List<CouponEntity> getCoupons(@NotEmpty final List<String> codes) {

        final ArrayList<CouponEntity> foundCouponEntities = new ArrayList<>();

        codes.forEach(code -> getCoupon(code).ifPresentOrElse(
            foundCouponEntities::add,
            () -> log.warn("Coupon code '{}' not found, skipping", code)));

        return foundCouponEntities;
    }
}
