package schwarz.jobs.interview.coupon.web.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import schwarz.jobs.interview.coupon.core.persistence.entity.CouponEntity;
import schwarz.jobs.interview.coupon.core.services.CouponService;
import schwarz.jobs.interview.coupon.core.services.model.BasketDomain;
import schwarz.jobs.interview.coupon.web.dto.request.ApplyCouponRequestDTO;
import schwarz.jobs.interview.coupon.web.dto.request.CreateCouponRequestDTO;

@RestController
@RequestMapping("/api")
@Slf4j
@Validated
public class CouponResourceController {

    private final CouponService couponService;

    public CouponResourceController(final CouponService couponService) {
        this.couponService = couponService;
    }

    /**
     * @param applyCouponRequestDTO
     * @return
     */
    //@ApiOperation(value = "Applies currently active promotions and coupons from the request to the requested Basket - Version 1")
    @PostMapping(value = "/apply")
    public ResponseEntity<BasketDomain> applyCoupon(
        //@ApiParam(value = "Provides the necessary basket and customer information required for the coupon application", required = true)
        @RequestBody @Valid final ApplyCouponRequestDTO applyCouponRequestDTO) {

        log.info("Applying coupon");

        final Optional<BasketDomain> basket =
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
    public ResponseEntity<List<CouponEntity>> getCoupons(@RequestParam @NotEmpty final List<String> codes) {

        return ResponseEntity.ok(couponService.getCoupons(codes));
    }
}
