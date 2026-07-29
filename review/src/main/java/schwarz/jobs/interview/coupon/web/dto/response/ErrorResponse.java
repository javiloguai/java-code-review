package schwarz.jobs.interview.coupon.web.dto.response;

import java.time.Instant;
import java.util.Map;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponse {

    Instant timestamp;

    int status;

    String error;

    String message;

    Map<String, String> fieldErrors;

}
