package schwarz.jobs.interview.coupon.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import schwarz.jobs.interview.coupon.constants.MapperConstants;
import schwarz.jobs.interview.coupon.core.services.model.command.CreateCouponCommand;
import schwarz.jobs.interview.coupon.web.dto.request.CreateCouponRequest;

/**
 * The CreateCouponCommand request mapper.
 */
@Mapper(componentModel = MapperConstants.COMPONENT_MODEL)
public interface CreateCouponRequestMapper extends CommandMapper<CreateCouponRequest, CreateCouponCommand> {

    CreateCouponRequestMapper INSTANCE = Mappers.getMapper(CreateCouponRequestMapper.class);

    /**
     * Gets mapper.
     *
     * @return the mapper
     */
    static CreateCouponRequestMapper getMapper() {
        return Mappers.getMapper(CreateCouponRequestMapper.class);
    }



}
