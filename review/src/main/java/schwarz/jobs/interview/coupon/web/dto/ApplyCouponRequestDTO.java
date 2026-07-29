package schwarz.jobs.interview.coupon.web.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import schwarz.jobs.interview.coupon.core.services.model.Basket;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyCouponRequestDTO {

    @NotBlank
    private String code;

    @NotNull
    @Valid
    private Basket basket;

}
