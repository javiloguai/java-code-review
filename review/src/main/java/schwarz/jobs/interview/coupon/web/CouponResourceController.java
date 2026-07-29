package schwarz.jobs.interview.coupon.web;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import schwarz.jobs.interview.coupon.core.domain.CouponEntity;
import schwarz.jobs.interview.coupon.core.services.CouponService;
import schwarz.jobs.interview.coupon.core.services.model.Basket;
import schwarz.jobs.interview.coupon.web.dto.ApplyCouponRequestDTO;
import schwarz.jobs.interview.coupon.web.dto.CreateCouponRequestDTO;
import schwarz.jobs.interview.coupon.web.dto.GetCouponsRequestDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class CouponResourceController {

    private final CouponService couponService;

    /**
     * @param applyCouponRequestDTO
     * @return
     */
    //@ApiOperation(value = "Applies currently active promotions and coupons from the request to the requested Basket - Version 1")
    @PostMapping(value = "/apply")
    public ResponseEntity<Basket> applyCoupon(
        //@ApiParam(value = "Provides the necessary basket and customer information required for the coupon application", required = true)
        @RequestBody @Valid final ApplyCouponRequestDTO applyCouponRequestDTO) {

        log.info("Applying coupon");

        final Optional<Basket> basket =
            couponService.apply(applyCouponRequestDTO.getBasket(), applyCouponRequestDTO.getCode());

        if (basket.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        log.info("Applied coupon");

        return ResponseEntity.ok().body(basket.get());
    }

    @PostMapping("/create")
    public ResponseEntity<CouponEntity> createCoupon(@RequestBody @Valid final CreateCouponRequestDTO couponDTO) {

        final CouponEntity couponEntity = couponService.createCoupon(couponDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(couponEntity);
    }

    @GetMapping("/coupons")
    public ResponseEntity<List<CouponEntity>> getCoupons(@RequestBody @Valid final GetCouponsRequestDTO couponRequestDTO) {

        return ResponseEntity.ok(couponService.getCoupons(couponRequestDTO));
    }
}
