package schwarz.jobs.interview.coupon.web.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request body for creating a new coupon.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCouponRequest {

    @NotNull
    @Positive
    private BigDecimal discount;

    @NotBlank
    private String code;

    @PositiveOrZero
    private BigDecimal minBasketValue;

}
