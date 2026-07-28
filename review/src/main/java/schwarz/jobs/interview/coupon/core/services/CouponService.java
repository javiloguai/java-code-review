package schwarz.jobs.interview.coupon.core.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import schwarz.jobs.interview.coupon.core.domain.CouponEntity;
import schwarz.jobs.interview.coupon.core.repository.CouponRepository;
import schwarz.jobs.interview.coupon.core.services.model.Basket;
import schwarz.jobs.interview.coupon.web.dto.CouponDTO;
import schwarz.jobs.interview.coupon.web.dto.CouponRequestDTO;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    public Optional<CouponEntity> getCoupon(final String code) {
        return couponRepository.findByCode(code);
    }

    public Optional<Basket> apply(final Basket basket, final String code) {

        return getCoupon(code).map(couponEntity -> {

            if (basket.getValue().doubleValue() >= 0) {

                if (basket.getValue().doubleValue() > 0) {

                    basket.applyDiscount(couponEntity.getDiscount());

                } else if (basket.getValue().doubleValue() == 0) {
                    return basket;
                }

            } else {
                System.out.println("DEBUG: TRIED TO APPLY NEGATIVE DISCOUNT!");
                throw new RuntimeException("Can't apply negative discounts");
            }

            return basket;
        });
    }

    public CouponEntity createCoupon(final CouponDTO couponDTO) {

        CouponEntity couponEntity = null;

        try {
            couponEntity = CouponEntity.builder()
                .code(couponDTO.getCode().toLowerCase())
                .discount(couponDTO.getDiscount())
                .minBasketValue(couponDTO.getMinBasketValue())
                .build();

        } catch (final NullPointerException e) {

            // Don't coupon when code is null
        }

        return couponRepository.save(couponEntity);
    }

    public List<CouponEntity> getCoupons(final CouponRequestDTO couponRequestDTO) {

        final ArrayList<CouponEntity> foundCouponEntities = new ArrayList<>();

        couponRequestDTO.getCodes().forEach(code -> foundCouponEntities.add(couponRepository.findByCode(code).get()));

        return foundCouponEntities;
    }
}
