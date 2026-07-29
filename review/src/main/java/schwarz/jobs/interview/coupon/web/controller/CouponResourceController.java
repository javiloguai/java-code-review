package schwarz.jobs.interview.coupon.web.controller;


import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import schwarz.jobs.interview.coupon.core.services.CouponService;
import schwarz.jobs.interview.coupon.core.services.model.domain.BasketDomain;
import schwarz.jobs.interview.coupon.core.services.model.domain.CouponDomain;
import schwarz.jobs.interview.coupon.web.dto.request.ApplyCouponRequest;
import schwarz.jobs.interview.coupon.web.dto.request.CreateCouponRequest;
import schwarz.jobs.interview.coupon.web.dto.response.BasketResponse;
import schwarz.jobs.interview.coupon.web.dto.response.CouponResponse;
import schwarz.jobs.interview.coupon.web.mapper.*;

/**
 * REST endpoints for coupons: apply, create and look up.
 */
@RestController
@RequestMapping("/api")
@Slf4j
@Validated
public class CouponResourceController {

    private final CouponService couponService;
    private final BasketRequestMapper basketRequestMapper;
    private final BasketResponseMapper basketResponseMapper;
    private final CreateCouponRequestMapper createCouponRequestMapper;
    private final CouponResponseMapper couponResponseMapper;

    public CouponResourceController(final CouponService couponService, BasketRequestMapper basketRequestMapper, BasketResponseMapper basketResponseMapper, CreateCouponRequestMapper createCouponRequestMapper, CouponResponseMapper couponResponseMapper) {
        this.couponService = couponService;
        this.basketRequestMapper = basketRequestMapper;
        this.basketResponseMapper = basketResponseMapper;
        this.createCouponRequestMapper = createCouponRequestMapper;
        this.couponResponseMapper = couponResponseMapper;
    }

    /**
     * Applies a coupon to a basket.
     */
    //@ApiOperation(value = "Applies currently active promotions and coupons from the request to the requested Basket - Version 1")
    @PostMapping(value = "/apply")
    public ResponseEntity<BasketResponse> applyCoupon(
        //@ApiParam(value = "Provides the necessary basket and customer information required for the coupon application", required = true)
        @RequestBody @Valid final ApplyCouponRequest applyCouponRequest) {

        log.info("Applying coupon");

        final BasketDomain basket =
            couponService.apply(basketRequestMapper.toDomain(applyCouponRequest.getBasket()), applyCouponRequest.getCode());

        log.info("Applied coupon");

        return ResponseEntity.ok().body(basketResponseMapper.toResponse(basket));
    }

    /**
     * Creates a new coupon.
     */
    @PostMapping("/create")
    public ResponseEntity<CouponResponse> createCoupon(@RequestBody @Valid final CreateCouponRequest createCouponRequest) {

        final CouponDomain coupon = couponService.createCoupon(createCouponRequestMapper.toCommand(createCouponRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(couponResponseMapper.toResponse(coupon));
    }

    /**
     * Looks up coupons by code. Unknown codes are skipped, not an error.
     */
    @GetMapping("/coupons")
    public ResponseEntity<List<CouponResponse>> getCoupons(@RequestParam @NotEmpty final List<String> codes) {

        return ResponseEntity.ok(couponResponseMapper.toResponses(couponService.getCoupons(codes)));
    }
}
