package schwarz.jobs.interview.coupon.core.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import schwarz.jobs.interview.coupon.core.domain.CouponEntity;
import schwarz.jobs.interview.coupon.core.repository.CouponRepository;
import schwarz.jobs.interview.coupon.core.services.impl.CouponServiceImpl;
import schwarz.jobs.interview.coupon.core.services.model.Basket;
import schwarz.jobs.interview.coupon.web.dto.CreateCouponRequestDTO;
import schwarz.jobs.interview.coupon.web.dto.GetCouponsRequestDTO;

@ExtendWith(SpringExtension.class)
public class CouponServiceTest {

    @InjectMocks
    private CouponServiceImpl couponService;

    @Mock
    private CouponRepository couponRepository;

    @Test
    public void createCoupon() {
        CreateCouponRequestDTO dto = CreateCouponRequestDTO.builder()
            .code("12345")
            .discount(BigDecimal.TEN)
            .minBasketValue(BigDecimal.valueOf(50))
            .build();

        couponService.createCoupon(dto);

        verify(couponRepository, times(1)).save(any());
    }

    @Test
    public void test_apply_coupon_method() {

        final Basket firstBasket = Basket.builder()
            .value(BigDecimal.valueOf(100))
            .build();

        when(couponRepository.findByCode("1111")).thenReturn(Optional.of(CouponEntity.builder()
            .code("1111")
            .discount(BigDecimal.TEN)
            .minBasketValue(BigDecimal.valueOf(50))
            .build()));

        Optional<Basket> optionalBasket = couponService.apply(firstBasket, "1111");

        assertThat(optionalBasket).hasValueSatisfying(b -> {
            assertThat(b.getAppliedDiscount()).isEqualTo(BigDecimal.TEN);
            assertThat(b.isApplicationSuccessful()).isTrue();
        });

        final Basket secondBasket = Basket.builder()
            .value(BigDecimal.valueOf(0))
            .build();

        optionalBasket = couponService.apply(secondBasket, "1111");

        assertThat(optionalBasket).hasValueSatisfying(b -> {
            assertThat(b).isEqualTo(secondBasket);
            assertThat(b.isApplicationSuccessful()).isFalse();
        });

        final Basket thirdBasket = Basket.builder()
            .value(BigDecimal.valueOf(-1))
            .build();

        assertThatThrownBy(() -> {
            couponService.apply(thirdBasket, "1111");
        }).isInstanceOf(RuntimeException.class)
            .hasMessage("Can't apply negative discounts");
    }

    @Test
    public void should_test_get_Coupons() {

        GetCouponsRequestDTO dto = GetCouponsRequestDTO.builder()
            .codes(Arrays.asList("1111", "1234"))
            .build();

        when(couponRepository.findByCode(any()))
            .thenReturn(Optional.of(CouponEntity.builder()
                .code("1111")
                .discount(BigDecimal.TEN)
                .minBasketValue(BigDecimal.valueOf(50))
                .build()))
            .thenReturn(Optional.of(CouponEntity.builder()
                .code("1234")
                .discount(BigDecimal.TEN)
                .minBasketValue(BigDecimal.valueOf(50))
                .build()));

        List<CouponEntity> returnedCouponEntities = couponService.getCoupons(dto);

        assertThat(returnedCouponEntities.get(0).getCode()).isEqualTo("1111");

        assertThat(returnedCouponEntities.get(1).getCode()).isEqualTo("1234");
    }
}
