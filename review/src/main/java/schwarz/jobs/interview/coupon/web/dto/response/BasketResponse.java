package schwarz.jobs.interview.coupon.web.dto.response;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasketResponse {

    private BigDecimal value;

    private BigDecimal appliedDiscount;

    private boolean applicationSuccessful;

}
