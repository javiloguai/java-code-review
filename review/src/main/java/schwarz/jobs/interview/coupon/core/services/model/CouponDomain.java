package schwarz.jobs.interview.coupon.core.services.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponDomain {

    @NotNull
    private Long id;

    @NotNull
    private String code;

    @PositiveOrZero
    private BigDecimal discount;

    @PositiveOrZero
    private BigDecimal minBasketValue;

}
