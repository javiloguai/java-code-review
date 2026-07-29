package schwarz.jobs.interview.coupon.web.mapper;

/**
 * Mapper from requests to command dto
 *
 * @param <I> the request dto
 * @param <R> the command dto
 */
public interface CommandMapper<I, R> {

    /**
     * From request to dto r.
     *
     * @param input the input
     * @return the r
     */
    R toCommand(I input);


}
