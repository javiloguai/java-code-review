package schwarz.jobs.interview.coupon.web.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

/**
 * Mapper from Domain entity to response DTO
 *
 * @param <I> input
 * @param <R> Response
 */
public interface ResponseMapper<I, R> {

    /**
     * To response.
     *
     * @param input the input
     * @return the r
     */
    R toResponse(I input);

    /**
     * To responses.
     *
     * @param inputs the inputs
     * @return the list
     */
    List<R> toResponses(List<I> inputs);

}
